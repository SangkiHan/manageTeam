package com.manageTeam.util;

import com.manageTeam.entity.Position;

public class Util {
	
	public static Position setPosition(String strPosition) {
		Position position = null;
		switch (strPosition) {
		case "C":
			position = Position.C;
		case "PG":
			position = Position.PG;
		case "SG":
			position = Position.SG;
		case "SF":
			position = Position.SF;
		case "PF":
			position = Position.PF;
		}
		return position;
	}
}
