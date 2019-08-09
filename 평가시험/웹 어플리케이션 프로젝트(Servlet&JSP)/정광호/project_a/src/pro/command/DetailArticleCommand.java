package pro.command;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.dao.ThumbDAO;
import pro.jdbc.ConnectionProvider;
import pro.model.Comment;
import pro.model.DetailArticle;
import pro.model.Member;
import pro.model.Thumb;
import pro.service.CommentCountService;
import pro.service.CommentListService;
import pro.service.DetailArticleSearchService;
import pro.service.DetailArticleUpdateReadCountService;

public class DetailArticleCommand extends Command {
	private String formPage = "/WEB-INF/iframe/detail_article.jsp";
	private String submitPage = "/WEB-INF/submits/detail_article.jsp";
	private String errorPage = "/WEB-INF/errors/detail_article.jsp";

	private DetailArticleUpdateReadCountService daurcService = new DetailArticleUpdateReadCountService();
	private DetailArticleSearchService dasService = new DetailArticleSearchService();
	private CommentListService clService = new CommentListService();
	private CommentCountService ccService = new CommentCountService();
	ThumbDAO thumbeDAO = new ThumbDAO();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("login_member");

		String strArticle_id = request.getParameter("article_id");

		int article_id = 0;
		try {
			article_id = Integer.parseInt(strArticle_id);

		} catch (Exception e) {
			request.setAttribute("errorMsg", "잘못된 접근입니다.");
			return errorPage;
		}

		DetailArticle model = new DetailArticle();
		model.setArticle_id(article_id);

		try (Connection conn = ConnectionProvider.getConnection()) {
			/////////////////////////////////////////////////////
			// 좋아요 저장구간
			Thumb tmodel = new Thumb();
			tmodel.setMember_id(member.getMember_id());
			tmodel.setArticle_id(article_id);
			request.setAttribute("thumb", thumbeDAO.selectOneArticleThumb(conn, tmodel).getArticle_thumb());
			/////////////////////////////////////////////////////

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", model);

			HashMap<String, Object> resultMap = null;

			if (request.getMethod().equals("GET")) {
				resultMap = daurcService.service(values);
				if (!(Boolean) resultMap.get("result")) {
					request.setAttribute("errorMsg", "조회수 갱신에 에러가 발생했습니다.");
					return errorPage;
				}
			}

			resultMap = dasService.service(values);

			if (!(Boolean) resultMap.get("result")) {
				request.setAttribute("errorMsg", "게시글을 찾을 수 없습니다.");
				return errorPage;
			}

			request.setAttribute("detailArticle", resultMap.get("detailArticle"));

			// 댓글 조회
			Comment comment = new Comment();
			comment.setArticle_id(article_id);
			values.put("model", comment);

			resultMap = ccService.service(values);
			request.setAttribute("commentSize", resultMap.get("commentSize"));
			resultMap = clService.service(values);
			request.setAttribute("commentList", resultMap.get("commentList"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return processForm(request, response);
	}
}
