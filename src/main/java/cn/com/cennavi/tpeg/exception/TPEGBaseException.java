package cn.com.cennavi.tpeg.exception;

import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.util.StrHelper;

@SuppressWarnings("rawtypes")
public class TPEGBaseException extends RuntimeException {

	private static final long serialVersionUID = 3447282960803744352L;

	protected static String getExceptionName() {
		return TPEGBaseException.class.getName();
	}

	public TPEGBaseException() {
		super();
	}

	public TPEGBaseException(String args, Object[] objs) {
		super(getExceptionName().concat(" Error : [").concat(StrHelper.getContent(args, objs))
				.concat("]"));
	}

	
    public TPEGBaseException(String args, Object[] objs, Class clazz, CoderItem ci,Exception e) {
		super(getExceptionName().concat(" Error : [").concat(StrHelper.getContent(args, objs))
				.concat("]").concat(
						clazz == null ? "" : " [Class Name : ".concat(clazz.getName())
								.concat(" ] ")).concat(
						ci == null ? "" : "[CoderItem ID : ".concat(ci.id()).concat(" ]")),e);

	}
	
	public TPEGBaseException(String args, Object[] objs, Class clazz, CoderItem ci) {
		super(getExceptionName().concat(" Error : [").concat(StrHelper.getContent(args, objs))
				.concat("]").concat(
						clazz == null ? "" : " [Class Name : ".concat(clazz.getName())
								.concat(" ] ")).concat(
						ci == null ? "" : "[CoderItem ID : ".concat(ci.id()).concat(" ]")));

	}

	public TPEGBaseException(String args) {
		super(getExceptionName().concat(" Error : [").concat(args).concat("]"));
	}

	public TPEGBaseException(Throwable e) {
		super(getExceptionName().concat(" Error : [Unknown]"), e);
	}

	public TPEGBaseException(String args, Object[] objs, Throwable e) {
		super(getExceptionName().concat(" Error : [").concat(StrHelper.getContent(args, objs))
				.concat("]"), e);
	}

	public TPEGBaseException(String args, Throwable e) {
		super(getExceptionName().concat(" Error : [").concat(args).concat("]"), e);
	}

}
