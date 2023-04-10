//package myboard;
//
//import static myboard.JdbcUtil.getConnection;  // import static Ȯ��
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
//        System.out.println("======�Խù� ���======");
//        System.out.print("�ۼ��� : ");
//        String writer = sc.next();
//        System.out.print("��й�ȣ : ");
//        String pwd = sc.next();
//        System.out.print("���� : ");
//        String title = sc.next();
//        System.out.print("�̸��� : ");
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
//                System.out.println("��� �Ϸ�!");
//                commit(con);
//            } else {
//                System.out.println("��� ����!");
//                rollBack(con);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally{
//            close(pstmt); // PreparedStatement �� Statement�� ����ϱ⿡ ��ĳ������ �Ͼ��.
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
//                System.out.printf("�۹�ȣ : %d, �ۼ��� : %s, ���� : %s, �̸��� : %s\n",
//                        rs.getInt("id"),
//                        rs.getString("writer"),
//                        rs.getString("title"),
//                        rs.getString("email")
//                );
//                while (rs.next()) {
//                    System.out.printf("�۹�ȣ : %d, �ۼ��� : %s, ���� : %s, �̸��� : %s\n",
//                            rs.getInt("id"),
//                            rs.getString("writer"),
//                            rs.getString("title"),
//                            rs.getString("email")
//                    );
//                }
//
//            } else{
//                System.out.println("��ϵ� ���� �����ϴ�!");
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
//        System.out.println("�˻��� ������ �����ϼ���");
//        System.out.print("1. �۹�ȣ 2. �ۼ��� 3. ���� > ");
//        String searchVal = null;
//        int menu = sc.nextInt();
//
//        switch(menu){
//            case 1:
//                System.out.print("�˻��� �۹�ȣ�� �Է��ϼ��� > ");
//                searchVal = sc.next();
//                break;
//            case 2:
//                System.out.print("�˻��� �ۼ��ڸ� �Է��ϼ��� > ");
//                searchVal = sc.next();
//                break;
//            case 3:
//                System.out.print("�˻��� ������ �Է��ϼ��� > ");
//                searchVal = sc.next();
//                break;
//            default:
//                System.out.println("�߸��� �Է��Դϴ�!");
//        }
//        ArrayList<BoardVO> articles = getArticle(menu, searchVal);
//        if(articles.size() == 0){
//            System.out.println("�ش��ϴ� ������ ����� �����ϴ�!");
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
//        System.out.println("���� ������ �Է��ϼ���");
//        System.out.print("1. �۹�ȣ ���� 2. �ۼ��� ���� ���� > ");
//        int menu = sc.nextInt();
//        switch(menu){
//            case 1:
//                System.out.print("������ �۹�ȣ�� �Է��ϼ��� > ");
//                searchVal = sc.next();
//                break;
//            case 2:
//                System.out.print("������ ����  �ۼ��ڸ� �Է��ϼ��� (�ش� �ۼ����� ���� ��� �����մϴ�) > ");
//                searchVal = sc.next();
//                break;
//            default:
//                System.out.println("�߸��� �Է��Դϴ�.");
//        }
//
//        result = deleteArticle(menu, searchVal);
//
//        if (result > 0) {
//            System.out.println("���� ����!");
//            commit(con);
//        } else {
//            System.out.println("���� ����!");
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
//        System.out.println("������ ���� ��ȣ�� �Է��ϼ���.");
//        System.out.print("���̵� > ");
//        String id = sc.next();
//        ArrayList<BoardVO> article = getArticle(1, id);
//        BoardVO boardVO = article.get(0);
//        String userInput;
//        String writer = boardVO.getWriter();
//        String pwd = boardVO.getPwd();
//        String title = boardVO.getTitle();
//        String email = boardVO.getEmail();
//
//        System.out.println("������ �����͸� �Է��ϼ���.");
//        System.out.println("������ �����ϰ� ���� �ʴٸ� Enter�� ��������");
//
//        sc.nextLine(); // �̰� �Է¾����ָ� �۹�ȣ ������ nextInt�� �����ִ� ���๮�ڰ� �Ʒ���
//        // userInput = sc.nextLine()�� �������� �ۼ��� �Է��� ������ �������� ó���ȴ�.
//        System.out.println("���� �ۼ��� : " + writer);
//        System.out.print("������ �ۼ��� : ");
//        userInput = sc.nextLine();
//        System.out.println(userInput);
//        if(userInput.length() != 0){
//            writer = userInput;
//        }
//        System.out.println("���� ��й�ȣ : " + pwd);
//        System.out.print("������ ��й�ȣ : ");
//        userInput = sc.nextLine();
//        if(userInput.length() != 0){
//            pwd = userInput;
//        }
//        System.out.println("���� ���� : " + title);
//        System.out.print("������ ���� : ");
//        userInput = sc.nextLine();
//        if(userInput.length() != 0){
//            title = userInput;
//        }
//        System.out.println("���� �̸��� : " + email);
//        System.out.print("������ �̸��� : ");
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
//            System.out.println("���� �Ϸ�!");
//            commit(con);
//        } else {
//            System.out.println("���� ����!");
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
