package cn.com.cennavi.tpeg.exception;

import cn.com.cennavi.codec.core.annotation.CoderItem;

@SuppressWarnings("rawtypes")
public class TPEGDecodeException extends TPEGBaseException {

	private static final long serialVersionUID = -1832025652035203576L;

	protected static String getExceptionName() {
		return TPEGDecodeException.class.getName();
	}
	
	public TPEGDecodeException(String args, Object[] objs, Class clazz, CoderItem ci){
		super(args,objs,clazz,ci);
	}
	
	public TPEGDecodeException(String args, Object[] objs, Class clazz, CoderItem ci,Exception e) {
		super(args,objs,clazz,ci,e);
	}

	public TPEGDecodeException(String args, Object[] objs) {
		super(args, objs);
	}

	public TPEGDecodeException(String args) {
		super(args);
	}

	public TPEGDecodeException() {
		super();
	}

	public TPEGDecodeException(String args, Object[] objs, Throwable e) {
		super(args, objs, e);
	}

	public TPEGDecodeException(String args, Throwable e) {
		super(args, e);
	}

	public TPEGDecodeException(Throwable e) {
		super(e);
	}

}
