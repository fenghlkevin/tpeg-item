package cn.com.cennavi.tpeg.exception;

public class TPEGParseException extends TPEGBaseException {

	private static final long serialVersionUID = -444925744841793431L;
	
	protected static String getExceptionName() {
		return TPEGParseException.class.getName();
	};
	

	public TPEGParseException(String args, Object[] objs) {
		super(args, objs);
	}

	public TPEGParseException(String args) {
		super(args);
	}

	public TPEGParseException() {
		super();
	}

	public TPEGParseException(String args, Object[] objs, Throwable e) {
		super(args, objs, e);
	}

	public TPEGParseException(String args, Throwable e) {
		super(args, e);
	}

	public TPEGParseException(Throwable e) {
		super(e);
	}

}
