package main;

import java.io.Serializable;

public class VoResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long parameter;

	public Long getParameter() {
		return parameter;
	}

	public void setParameter(Long parameter) {
		this.parameter = parameter;
	}
	
}
