package cn.com.cennavi.tpeg.exception;

import cn.com.cennavi.codec.core.annotation.CoderItem;

@SuppressWarnings("rawtypes")
public class TPEGEncodeException extends TPEGBaseException {

	private static final long serialVersionUID = -1832025652035203576L;

	protected static String getExceptionName() {
		return TPEGEncodeException.class.getName();
	}
	
	public TPEGEncodeException(String args, Object[] objs, Class clazz, CoderItem ci){
		super(args,objs,clazz,ci);
	}
	
	public TPEGEncodeException(String args, Object[] objs, Class clazz, CoderItem ci,Exception e) {
		super(args,objs,clazz,ci,e);
	}

	public TPEGEncodeException(String args, Object[] objs) {
		super(args, objs);
	}

	public TPEGEncodeException(String args) {
		super(args);
	}

	public TPEGEncodeException() {
		super();
	}

	public TPEGEncodeException(String args, Object[] objs, Throwable e) {
		super(args, objs, e);
	}

	public TPEGEncodeException(String args, Throwable e) {
		super(args, e);
	}

	public TPEGEncodeException(Throwable e) {
		super(e);
	}

}
