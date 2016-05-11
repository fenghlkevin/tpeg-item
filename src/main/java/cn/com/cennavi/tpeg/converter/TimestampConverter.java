package cn.com.cennavi.tpeg.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.cennavi.codec.core.annotation.IMapperConverter;

public class TimestampConverter implements IMapperConverter {

	@Override
	public String converter(String arg) throws Exception {
		Date date = new Date(Long.valueOf(arg + "000"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String temp = sdf.format(date);
		return arg+"  ("+temp+")";
	}

}
