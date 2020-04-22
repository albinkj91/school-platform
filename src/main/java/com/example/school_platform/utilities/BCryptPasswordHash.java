package com.example.school_platform.utilities;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordHash {

	public String hash(String password){
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public boolean verifyHash(String password, String hash){
		return BCrypt.checkpw(password, hash);
	}
}
