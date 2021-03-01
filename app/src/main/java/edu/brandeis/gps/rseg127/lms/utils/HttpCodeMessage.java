// this takes an http code and wrangles it into the error template
package edu.brandeis.gps.rseg127.lms.utils;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HttpCodeMessage extends Message {
	private Integer code;

	public HttpCodeMessage(Object status) {
		super();

		setCode(Integer.valueOf(status.toString()));
		setValsByStatus();
	}
	
	private void setValsByStatus() {
		// set types based on the code
		if (HttpStatus.valueOf(code).is1xxInformational()) {
			setType("default");
		}
		
		if (HttpStatus.valueOf(code).is2xxSuccessful()) {
			setType("good");
		}
		
		if (HttpStatus.valueOf(code).is3xxRedirection()) {
			setType("warn");
		}
		
		if (HttpStatus.valueOf(code).is4xxClientError()) {
			setType("error");
		}
		
		if (HttpStatus.valueOf(code).is5xxServerError()) {
			setType("error");
		}

		// set message to vanilla http error name
		setMessage(HttpStatus.valueOf(code).getReasonPhrase());
	}
}
