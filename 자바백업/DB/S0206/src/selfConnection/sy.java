package selfConnection;
import java.util.ArrayList;

import oracle.net.aso.s;

import java.sql.*;

public class sy {

	public static void main(String[] args) {

		sqlConnect sc = new sqlConnect();
		
		sc.connect();
		sc.inputList();
		sc.insert();
		
	}

}
