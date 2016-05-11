package cn.com.cennavi.tpeg.exception;

public class TPEGOutOfRangeException extends TPEGBaseException {

	private static final long serialVersionUID = -7282845436961426967L;
	
	protected static String getExceptionName() {
		return TPEGOutOfRangeException.class.getName();
	};
	public TPEGOutOfRangeException(String args, Object[] objs) {
		super(args, objs);
	}

	public TPEGOutOfRangeException(String args) {
		super(args);
	}

	public TPEGOutOfRangeException() {
		super();
	}

	public TPEGOutOfRangeException(String args, Object[] objs, Throwable e) {
		super(args, objs, e);
	}

	public TPEGOutOfRangeException(String args, Throwable e) {
		super(args, e);
	}

	public TPEGOutOfRangeException(Throwable e) {
		super(e);
	}
}
