package com.acalo.wildfly.domain;

public class BuilderTest {
	private String name;
	private String country;

	public static class Builder {
		private String name;
		private String country;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder country(String country) {
			this.country = country;
			return this;
		}

		public BuilderTest build() {
			return new BuilderTest(this);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private BuilderTest(Builder builder) {
		this.name = builder.name;
		this.country = builder.country;
	}
}
