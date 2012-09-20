package net.upaste;

import java.sql.SQLException;

import spark.servlet.SparkApplication;

public class UPasteApplication implements SparkApplication
{

	@Override
	public void init() {
		try {
			UPaste p = new UPaste();
			p.init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
