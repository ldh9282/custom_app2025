package com.custom.app2025.reactive.sec02;

import com.custom.app2025.shared.model.CustomMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//@Builder
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Log4j2
public class StudentDTO {

	private String name;
	
	private int age;
	
	
	
	public static class Builder {
		private StudentDTO instance;
		
		public Builder() {
			this.instance = new StudentDTO();
		}
		
		public Builder name(String name) {
			this.instance.name = name;
			return this;
		}
		
		public Builder age(int age) {
			this.instance.age = age;
			return this;
		}
		
		public StudentDTO build() {
			return this.instance;
		}
	}
	
	public static Builder builder() {
		Builder builder = new Builder();
		return builder;
	}
	
	@Override
	public String toString() {
		return CustomMap.objectToString(this, new String[] { "log" });
	}
	
	public static void main(String[] args) {
		StudentDTO studentDTO = StudentDTO.builder()
				.name("amy")
				.age(10)
				.build();
		log.debug(studentDTO);
	}
}
