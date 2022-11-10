package com.tarun.ses;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

@ExcludeFromJacocoGeneratedReport
public class Utility {
	private Utility() {
		throw new IllegalStateException("Utility class");
	}

	static ModelMapper mapper = new ModelMapper();

	public static void customMapperConvertor() {
		Converter<String, String> stringTrimmer = new AbstractConverter<>() {
			protected String convert(String source) {
				return source == null ? null : source.trim();
			}
		};
		mapper.addConverter(stringTrimmer);
	}

	public static Object map(Object source, Class<?> destinationType) {
		return mapper.map(source, destinationType);
	}
}
