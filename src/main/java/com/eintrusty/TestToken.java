package com.eintrusty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.eintrusty.utility.StringUtil;

public class TestToken {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> role = new ArrayList();
		role.add("ADMIN");
		role.add("USER");
		
		
		String token = StringUtil.buildingBodyTokenUsernameAndRole("arop","//" ,role,";");
		System.out.println("Token " + token);
		//String[] getToken = token.split("//");
		//System.out.println("username " + getToken[1]);
		Map<String,Object> datas = StringUtil.extractUserNameAndRoleFromBodyToken(token,"//",";");
		List<String> rolesU = (List<String>) datas.get("roles");
		
		System.out.println(rolesU.size());
		for (String string : rolesU) {
			System.out.println(string);
			System.out.println("OK");
		}

	}

}
