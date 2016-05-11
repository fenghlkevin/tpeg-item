package cn.com.cennavi.tpeg.item;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import cn.com.cennavi.codec.Item;
import cn.com.cennavi.codec.core.annotation.CoderItem;
import cn.com.cennavi.codec.core.annotation.CoderLinks;
import cn.com.cennavi.codec.core.annotation.CoderItem.CoderItemType;
import cn.com.cennavi.codec.core.annotation.CoderLinks.EncoderBuildOrder;
import cn.com.cennavi.codec.core.base.AbstractCoderItem;
import cn.com.cennavi.codec.util.NTransUtil;
import cn.com.cennavi.codec.util.StrHelper;
import cn.com.cennavi.tpeg.exception.TPEGDecodeException;

@CoderLinks(compileOrder = @EncoderBuildOrder(order = {TPEG.TRANSPORTFRAME}), buildOrder = @EncoderBuildOrder(order = {TPEG.TRANSPORTFRAME}))
public class TPEG extends AbstractCoderItem implements Item {

	private static final long serialVersionUID = 4165412582544258807L;

	public static final String TRANSPORTFRAME = "frames";

	@CoderItem(id = TPEG.TRANSPORTFRAME, type = CoderItemType.LIST, length = -99999)
	private List<TransportFrame> frames;

	public List<TransportFrame> getFrames() {
		return frames;
	}

	public void setFrames(List<TransportFrame> frames) {
		this.frames = frames;
	}

	public TransportFrame addFrames() {
		if (frames == null) {
			frames = new ArrayList<TransportFrame>();
		}
		TransportFrame tf = new TransportFrame();
		tf.setItemName(TPEG.TRANSPORTFRAME);
		frames.add(tf);
		return tf;
	}

	public int getFramesCount() {
		if (frames == null) {
			return 0;
		}
		return frames.size();
	}

	@Override
	public void decoding() {
		try {
			while (this.getEncodedByteStream().available() > 0) {
				TransportFrame transportFrame = this.addFrames();
				// this.getEncodedByteStream().mark(0);
				byte[] head = new byte[2];
				byte[] serviceLength = new byte[2];
				byte[] headCrc = new byte[2];
				byte[] type = new byte[1];

				this.getEncodedByteStream().read(head);
				this.getEncodedByteStream().read(serviceLength);
				this.getEncodedByteStream().read(headCrc);
				this.getEncodedByteStream().read(type);

				int servicelength = NTransUtil.hexByteToInt(serviceLength);// hexbyteArray2Int
				byte[] service = new byte[servicelength];
				this.getEncodedByteStream().read(service);
				byte[] allfreame = StrHelper.integrateByteArray(head, serviceLength, headCrc, type, service);

				// ByteArrayInputStream frameStream = new
				// ByteArrayInputStream(allfreame);

				transportFrame.setEncodedStream(new ByteArrayInputStream(allfreame));
				transportFrame.decoding();
			}
		} catch (Throwable e) {
			if (e instanceof TPEGDecodeException) {
				throw (TPEGDecodeException) e;
			}
			throw new TPEGDecodeException("Transfrom TPEG BIN to Object ERROR!", e);
		}
	}

	public static void main(String[] args) throws Throwable {
		String path = "/home/fengheliang/test.tpg";
		FileInputStream fis = new FileInputStream(path);
		byte[] bs = new byte[fis.available()];
		fis.read(bs);
		fis.close();
		TPEG t=new TPEG();
		t.setEncodedStream(new ByteArrayInputStream(bs));
		t.decoding();
		System.out.println();
	}
}
