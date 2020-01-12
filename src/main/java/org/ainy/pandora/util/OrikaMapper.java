package org.ainy.pandora.util;

import java.util.List;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-11-06 22:38
 * @Description JavaBean映射工具
 */
public class OrikaMapper {

	private static final MapperFacade mapperFacade;

	static {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().useAutoMapping(true).mapNulls(true).build();
		mapperFacade = mapperFactory.getMapperFacade();
	}

	public static <S, D> void copy(S from, D to) {
		mapperFacade.map(from, to);
	}

	public static <S, D> D copy(S from, Class<D> clazz) {
		return mapperFacade.map(from, clazz);
	}

	public static MapperFacade getMapperFacade() {
		return mapperFacade;
	}

	public static <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
		return mapperFacade.mapAsList(source, destinationClass);
	}
	
}
