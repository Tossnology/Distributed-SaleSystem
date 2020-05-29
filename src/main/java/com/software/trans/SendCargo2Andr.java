package com.software.trans;

import java.util.ArrayList;
import java.util.List;

public class SendCargo2Andr {
	private List<ReceiveCargo> answers = new ArrayList<ReceiveCargo>();
	
	public List<ReceiveCargo> getAnswers() {
		return answers;
	}

	public void setAnswers(List<ReceiveCargo> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "test [answers=" + answers + "]";
	}

	/**
	 * @param answers
	 */
	public SendCargo2Andr(List<ReceiveCargo> answers) {
		super();
		this.answers = answers;
	}
}
