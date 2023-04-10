//package myboard;
//
//import static myboard.JdbcUtil.getConnection;  // import static 확인
//import static myboard.JdbcUtil.close;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Scanner;
//import static myboard.JdbcUtil.*;
//
//public class BoardSVC {
//    Connection con;
//
//    public BoardVO getBoardVO(Scanner sc) {
//        System.out.println("======게시물 등록======");
//        System.out.print("작성자 : ");
//        String writer = sc.next();
//        System.out.print("비밀번호 : ");
//        String pwd = sc.next();
//        System.out.print("제목 : ");
//        String title = sc.next();
//        System.out.print("이메일 : ");
//        String email = sc.next();
//
//        return new BoardVO(0, writer, pwd, title, email);
//    }
//
//    public void writeArticle(Scanner sc) {
//        BoardVO board = getBoardVO(sc);
//        con = getConnection();
//        PreparedStatement pstmt = null;
//
//        String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
//        try {
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, board.getWriter());
//            pstmt.setString(2, board.getPwd());
//            pstmt.setString(3, board.getTitle());
//            pstmt.setString(4, board.getEmail());
//
//            int count = pstmt.executeUpdate();
//            if (count > 0) {
//                System.out.println("등록 완료!");
//                commit(con);
//            } else {
//                System.out.println("등록 실패!");
//                rollBack(con);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally{
//            close(pstmt); // PreparedStatement 가 Statement를 상속하기에 업캐스팅이 일어난다.
//            close(con);
//        }
//    }
//
//    public void showArticleList(){
//        con = getConnection();
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        String sql = "SELECT * FROM BOARD";
//
//        try {
//            stmt = con.createStatement();
//            rs = stmt.executeQuery(sql);
//            if(rs.next()){
//                System.out.printf("글번호 : %d, 작성자 : %s, 제목 : %s, 이메일 : %s\n",
//                        rs.getInt("id"),
//                        rs.getString("writer"),
//                        rs.getString("title"),
//                        rs.getString("email")
//                );
//                while (rs.next()) {
//                    System.out.printf("글번호 : %d, 작성자 : %s, 제목 : %s, 이메일 : %s\n",
//                            rs.getInt("id"),
//                            rs.getString("writer"),
//                            rs.getString("title"),
//                            rs.getString("email")
//                    );
//                }
//
//            } else{
//                System.out.println("등록된 글이 없습니다!");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            close(rs);
//            close(stmt);
//            close(con);
//        }
//    }
//
//    public void showArticle(Scanner sc) {
////        showArticleList();
//        System.out.println("검색할 조건을 선택하세요");
//        System.out.print("1. 글번호 2. 작성자 3. 제목 > ");
//        String searchVal = null;
//        int menu = sc.nextInt();
//
//        switch(menu){
//            case 1:
//                System.out.print("검색할 글번호를 입력하세요 > ");
//                searchVal = sc.next();
//                break;
//            case 2:
//                System.out.print("검색할 작성자를 입력하세요 > ");
//                searchVal = sc.next();
//                break;
//            case 3:
//                System.out.print("검색할 제목을 입력하세요 > ");
//                searchVal = sc.next();
//                break;
//            default:
//                System.out.println("잘못된 입력입니다!");
//        }
//        ArrayList<BoardVO> articles = getArticle(menu, searchVal);
//        if(articles.size() == 0){
//            System.out.println("해당하는 조건의 결과가 없습니다!");
//        }else {
//            for(BoardVO article : articles){
//                System.out.println(article);
//            }
//        }
//    }
//
//    private ArrayList<BoardVO> getArticle(int menu, String searchVal){
//        ArrayList<BoardVO> articles = new ArrayList<>();
//        ResultSet rs = null;
//        BoardVO boardVO = null;
//        PreparedStatement pstmt = null;
//        con = getConnection();
//        String sql = "SELECT * FROM BOARD WHERE ";
//        switch(menu){
//            case 1:
//                sql += "ID = ?";
//                break;
//            case 2:
//                sql += "WRITER = ?";
//                break;
//            case 3:
//                sql += "TITLE = ?";
//                break;
//        }
//
//        try {
//            pstmt = con.prepareStatement(sql);
//            switch(menu){
//                case 1:
//                    pstmt.setInt(1, Integer.parseInt(searchVal));
//                    break;
//                case 2:
//                case 3:
//                    pstmt.setString(1, searchVal);
//                    break;
//            }
//
//            rs = pstmt.executeQuery();
//
//            while(rs.next()){
//                boardVO = new BoardVO(
//                        rs.getInt("id"),
//                        rs.getString("writer"),
//                        rs.getString("pwd"),
//                        rs.getString("title"),
//                        rs.getString("email"));
//                articles.add(boardVO);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close(rs);
//            close(pstmt);
//            close(con);
//        }
//        return articles;
//    }
//
//    public void deleteArticle(Scanner sc) {
//        showArticleList();
//        int result = 0;
//        String searchVal = null;
//        System.out.println("삭제 조건을 입력하세요");
//        System.out.print("1. 글번호 삭제 2. 작성자 선택 삭제 > ");
//        int menu = sc.nextInt();
//        switch(menu){
//            case 1:
//                System.out.print("삭제할 글번호를 입력하세요 > ");
//                searchVal = sc.next();
//                break;
//            case 2:
//                System.out.print("삭제할 글의  작성자를 입력하세요 (해당 작성자의 글을 모두 삭제합니다) > ");
//                searchVal = sc.next();
//                break;
//            default:
//                System.out.println("잘못된 입력입니다.");
//        }
//
//        result = deleteArticle(menu, searchVal);
//
//        if (result > 0) {
//            System.out.println("삭제 성공!");
//            commit(con);
//        } else {
//            System.out.println("삭제 실패!");
//            rollBack(con);
//        }
//    }
//    private int deleteArticle(int menu, String searchVal){
//        PreparedStatement pstmt = null;
//        int count = 0;
//        con = getConnection();
//        String sql = "DELETE FROM BOARD WHERE ";
//        switch(menu){
//            case 1:
//                sql += "ID = ?";
//                break;
//            case 2:
//                sql += "WRITER = ?";
//                break;
//        }
//        try {
//            pstmt = con.prepareStatement(sql);
//            switch(menu){
//                case 1:
//                    pstmt.setInt(1, Integer.parseInt(searchVal));
//                    break;
//                case 2:
//                    pstmt.setString(1, searchVal);
//                    break;
//            }
//            count = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally{
//            close(pstmt);
//            close(con);
//        }
//        return count;
//    }
//
//    public void updateArticle2(Scanner sc) {
//        showArticleList();
//        System.out.println("수정할 글의 번호를 입력하세요.");
//        System.out.print("아이디 > ");
//        String id = sc.next();
//        ArrayList<BoardVO> article = getArticle(1, id);
//        BoardVO boardVO = article.get(0);
//        String userInput;
//        String writer = boardVO.getWriter();
//        String pwd = boardVO.getPwd();
//        String title = boardVO.getTitle();
//        String email = boardVO.getEmail();
//
//        System.out.println("수정할 데이터를 입력하세요.");
//        System.out.println("내용을 변경하고 싶지 않다면 Enter를 누르세요");
//
//        sc.nextLine(); // 이거 입력안해주면 글번호 선택의 nextInt에 남아있던 개행문자가 아래의
//        // userInput = sc.nextLine()에 들어가버려서 작성자 입력이 무조건 공백으로 처리된다.
//        System.out.println("기존 작성자 : " + writer);
//        System.out.print("수정할 작성자 : ");
//        userInput = sc.nextLine();
//        System.out.println(userInput);
//        if(userInput.length() != 0){
//            writer = userInput;
//        }
//        System.out.println("기존 비밀번호 : " + pwd);
//        System.out.print("수정할 비밀번호 : ");
//        userInput = sc.nextLine();
//        if(userInput.length() != 0){
//            pwd = userInput;
//        }
//        System.out.println("기존 제목 : " + title);
//        System.out.print("수정할 제목 : ");
//        userInput = sc.nextLine();
//        if(userInput.length() != 0){
//            title = userInput;
//        }
//        System.out.println("기존 이메일 : " + email);
//        System.out.print("수정할 이메일 : ");
//        userInput = sc.nextLine();
//        if(userInput.length() != 0){
//            email = userInput;
//        }
//
//        boardVO.setWriter(writer);
//        boardVO.setPwd(pwd);
//        boardVO.setTitle(title);
//        boardVO.setEmail(email);
//
//        int count = updateArticle2(boardVO);
//        if (count > 0) {
//            System.out.println("수정 완료!");
//            commit(con);
//        } else {
//            System.out.println("수정 실패!");
//            rollBack(con);
//        }
//    }
//
//    private int updateArticle2(BoardVO boardVO) {
//        int updateCount = 0;
//        con = getConnection();
//        PreparedStatement pstmt = null;
//        String sql = "UPDATE board SET writer = ?, pwd = ?, title = ?, email = ? WHERE id = ?";
//        try {
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, boardVO.getWriter());
//            pstmt.setString(2, boardVO.getPasswd());
//            pstmt.setString(3, boardVO.getSubject());
//            pstmt.setString(4, boardVO.getEmail());
//            pstmt.setInt(5, boardVO.getId());
//            updateCount = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close(pstmt);
//            close(con);
//        }
//
//        return updateCount;
//    }
//
//}
