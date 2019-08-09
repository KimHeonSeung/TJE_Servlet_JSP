package pro.command;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.dao.DetailArticleDAO;
import pro.dao.SimpleArticleDAO;
import pro.jdbc.ConnectionProvider;
import pro.model.DetailArticle;
import pro.service.ArticleListService;
import pro.service.ArticleSearchService;
import pro.service.CommentCountService;

public class ArticleCommand extends Command {
	private String formPage = "/WEB-INF/iframe/article.jsp";
	private String submitPage = "/WEB-INF/submits/article.jsp";
	private String errorPage = "/WEB-INF/errors/article.jsp";

	private ArticleListService alService = new ArticleListService();
	private ArticleSearchService asService = new ArticleSearchService();
	private CommentCountService ccService = new CommentCountService();
	private SimpleArticleDAO simpleArticleDAO = new SimpleArticleDAO();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {

		String strnow_page = request.getParameter("now_page");
		// 현재 페이지(디폴트 1)
		int now_page = 1;
		if(strnow_page!=null) {
			try {
				now_page = Integer.parseInt(strnow_page);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			int page_all_count = 0;
			int page_number = 0;
			int page_block = 0;

			// 페이지 나누는 단위
			int page_divide = 3;
			// 블록 나누는 단위
			int block_divide = 5;
			
			

			// db 접속해서 총개수 가져오기
			page_all_count = simpleArticleDAO.selectAll(conn).size();

			// 페이지 번호 가져오기
			page_number = page_all_count / page_divide;
			// 페이지 번호가 나눈 단위로 안 나눠지면 플러스시킴
			if (page_all_count % page_divide > 0) {
				page_number++;
			}

			// 페이지 블록 구하기
			page_block = page_number / block_divide;
			if (page_number % block_divide != 0) {
				page_block++;
			}

			request.setAttribute("page_all_count", page_all_count);
			request.setAttribute("page_number", page_number);
			request.setAttribute("page_block", page_block);
			request.setAttribute("page_divide", page_divide);
			request.setAttribute("now_page", now_page);

//		String a =	"select * from detailArticle limit " +((pageNo-1)*15) + ",15";

			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			request.setAttribute("articleList", simpleArticleDAO.selectarticlePage(conn, now_page, page_divide));

//			HashMap<String, Object> values = new HashMap<>();
//			values.put("conn", conn);
//			
//			HashMap<String, Object> resultMap = alService.service(values);
//			request.setAttribute("articleList", resultMap.get("articleList"));
//			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String searchItem = request.getParameter("searchItem");
		String searchValue = request.getParameter("searchValue");

		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("searchItem", searchItem);
			values.put("searchValue", searchValue);

			HashMap<String, Object> resultMap = asService.service(values);
			request.setAttribute("articleSearch", resultMap.get("articleSearch"));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return submitPage;
	}
}
