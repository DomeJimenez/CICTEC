package com.espe.cicte.ws.utils;

import java.awt.image.BufferedImage;

import org.apache.log4j.Logger;

import com.espe.cicte.ws.dto.BaseResponse;
import com.espe.cicte.ws.dto.Message;

import SecuGen.FDxSDKPro.jni.JSGFPLib;
import SecuGen.FDxSDKPro.jni.SGDeviceInfoParam;
import SecuGen.FDxSDKPro.jni.SGFDxDeviceName;
import SecuGen.FDxSDKPro.jni.SGFDxErrorCode;
import SecuGen.FDxSDKPro.jni.SGFDxTemplateFormat;
import SecuGen.FDxSDKPro.jni.SGFingerInfo;
import SecuGen.FDxSDKPro.jni.SGFingerPosition;
import SecuGen.FDxSDKPro.jni.SGImpressionType;
import SecuGen.FDxSDKPro.jni.SGPPPortAddr;

/**
 *
 * @author Pablo A. Villamar
 */
public class JSGD {

	/**
	 * 
	 */
	// Private instance variables
	private long deviceName;
	private long devicePort;
	private JSGFPLib fplib = null;
	private long ret;
	private int valQuality = 60;
	private int valSeconds = 20;
	// private byte[] regMin1 = new byte[400];
	// private byte[] regMin2 = new byte[400];
	/// private byte[] vrfMin = new byte[400];
	private SGDeviceInfoParam deviceInfo = new SGDeviceInfoParam();
	private BufferedImage imgRegistration1;
	private BufferedImage imgRegistration2;
	private BufferedImage imgVerification;
	private static int MINIMUM_QUALITY = 70; // User defined
	private static int MINIMUM_NUM_MINUTIAE = 20; // User defined
	private static int MAXIMUM_NFIQ = 2; // User defined

	private static Logger logger = org.apache.log4j.Logger.getLogger(JSGD.class);
	BaseResponse responseBase = new BaseResponse();
	Message messageResponse = new Message();
	
	public JSGD() {
		this.init();
	};

	public BaseResponse verify(byte[] regMin1, byte[] regMin2, byte[] vrfMin) {

		init();
		if (responseBase.getSuccess()) {
			long iError;
			long secuLevel = (long) (5);
			boolean[] matched = new boolean[1];
			matched[0] = false;

			iError = fplib.MatchTemplate(regMin1, vrfMin, secuLevel, matched);
			if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
				if (matched[0]) {
					logger.info("Verification Success (1st template)");
					messageResponse.setCode("008");
					messageResponse.setMessage("Verification Success (1st template)");
					responseBase.setMessage(messageResponse);
					responseBase.setSuccess(true);
					return responseBase;
				} else {
					iError = fplib.MatchTemplate(regMin2, vrfMin, secuLevel, matched);
					if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE)
						if (matched[0]) {
							logger.info("Verification Success (2nd template)");
							messageResponse.setCode("009");
							messageResponse.setMessage("Verification Success (2st template)");
							responseBase.setMessage(messageResponse);
							responseBase.setSuccess(true);
							return responseBase;
						} else {
							logger.info("Verification Fail");
							messageResponse.setCode("010");
							messageResponse.setMessage("Verification Fail");
							responseBase.setMessage(messageResponse);
							responseBase.setSuccess(false);
							return responseBase;
						}
					else {
						logger.info("Verification Attempt 2 Fail - MatchTemplate() Error : " + iError);
						messageResponse.setCode("011");
						messageResponse.setMessage("Verification Attempt 2 Fail - MatchTemplate() Error : " + iError);
						responseBase.setMessage(messageResponse);
						responseBase.setSuccess(false);
						return responseBase;
					}
				}
			} else {
				System.out.println("Verification Attempt 1 Fail - MatchTemplate() Error : " + iError);
				messageResponse.setCode("012");
				messageResponse.setMessage("Verification Attempt 1 Fail - MatchTemplate() Error : " + iError);
				responseBase.setMessage(messageResponse);
				responseBase.setSuccess(false);
				return responseBase;
			}
		}else {
			messageResponse.setCode("013");
			messageResponse.setMessage("Error en la inicializacion");
			responseBase.setMessage(messageResponse);
			responseBase.setSuccess(false);
			return responseBase;
		}
	}

	public boolean register(byte[] regMin1, byte[] regMin2) {
		int[] matchScore = new int[1];
		boolean[] matched = new boolean[1];
		long iError;
		long secuLevel = (long) (5);
		matched[0] = false;

		iError = fplib.MatchTemplate(regMin1, regMin2, secuLevel, matched);
		if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
			matchScore[0] = 0;
			iError = fplib.GetMatchingScore(regMin1, regMin2, matchScore);

			if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
				if (matched[0]) {
					logger.info("Registration Success, Matching Score: " + matchScore[0]);
					return true;
				} else
					logger.info("Registration Fail, Matching Score: " + matchScore[0]);

			} else
				logger.info("Registration Fail, GetMatchingScore() Error : " + iError);
			return false;
		} else
			logger.info("Registration Fail, MatchTemplate() Error : " + iError);
		return false;
	}

	public byte[] captureV1() {
		//BaseResponse response = new BaseResponse();
		if (0 == deviceInfo.imageDPI) {
			init();
		}
		byte[] vrfMin = new byte[400];
		//if (response.getSuccess()) {
			int[] quality = new int[1];
			int[] numOfMinutiae = new int[1];
			byte[] imageBuffer1 = ((java.awt.image.DataBufferByte) imgVerification.getRaster().getDataBuffer())
					.getData();
			long iError = SGFDxErrorCode.SGFDX_ERROR_NONE;

			iError = fplib.GetImageEx(imageBuffer1, valSeconds * 1000, 0, valQuality);
			fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1, quality);
			// this.jProgressBarV1.setValue(quality[0]); calidad
			SGFingerInfo fingerInfo = new SGFingerInfo();
			fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
			fingerInfo.ImageQuality = quality[0];
			fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
			fingerInfo.ViewNumber = 1;

			if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
				// this.jLabelVerifyImage.setIcon(new
				// ImageIcon(imgVerification.getScaledInstance(130,150,Image.SCALE_DEFAULT)));
				if (quality[0] < MINIMUM_QUALITY)
					logger.info(
							"GetImageEx() Baja Calidad de imagen [" + ret + "] [" + quality[0] + "]. Please try again");
				else {
					logger.info("GetImageEx() Success [" + ret + "]");

					iError = fplib.CreateTemplate(fingerInfo, imageBuffer1, vrfMin);
					if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
						long nfiqvalue;
						long ret2 = fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1,
								quality);
						nfiqvalue = fplib.ComputeNFIQ(imageBuffer1, deviceInfo.imageWidth, deviceInfo.imageHeight);
						ret2 = fplib.GetNumOfMinutiae(SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400, vrfMin, numOfMinutiae);

						if ((quality[0] >= MINIMUM_QUALITY) && (nfiqvalue <= MAXIMUM_NFIQ)
								&& (numOfMinutiae[0] >= MINIMUM_NUM_MINUTIAE)) {
							logger.info("Verification Capture PASS QC. Quality[" + quality[0] + "] NFIQ[" + nfiqvalue
									+ "] Minutiae[" + numOfMinutiae[0] + "]");
						} else {
							logger.info("Verification Imagen FAIL QC. Quality[" + quality[0] + "] NFIQ[" + nfiqvalue
									+ "] Minutiae[" + numOfMinutiae[0] + "]");
						}
					} else
						logger.info("CreateTemplate() Error : " + iError);
				}
			} else
				logger.info("GetImageEx() Error : " + iError);
		//}
		return vrfMin;
	}

	public byte[] captureR2() {
		BaseResponse response = new BaseResponse();
		if (0 == deviceInfo.imageDPI) {
			init();
		}
		byte[] regMin2 = new byte[400];
		//if (response.getSuccess()) {
			int[] quality = new int[1];
			int[] numOfMinutiae = new int[1];
			byte[] imageBuffer1 = ((java.awt.image.DataBufferByte) imgRegistration2.getRaster().getDataBuffer())
					.getData();
			long iError = SGFDxErrorCode.SGFDX_ERROR_NONE;

			iError = fplib.GetImageEx(imageBuffer1, valSeconds * 1000, 0, valQuality);
			fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1, quality);
			// this.jProgressBarR2.setValue(quality[0]); calidad
			SGFingerInfo fingerInfo = new SGFingerInfo();
			fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
			fingerInfo.ImageQuality = quality[0];
			fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
			fingerInfo.ViewNumber = 1;

			if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
				// this.jLabelRegisterImage2.setIcon(new
				// ImageIcon(imgRegistration2.getScaledInstance(130,150,Image.SCALE_DEFAULT)));
				if (quality[0] < MINIMUM_QUALITY)
					logger.info("GetImageEx() Success [" + ret + "] but image quality is [" + quality[0]
							+ "]. Please try again");
				else {
					logger.info("GetImageEx() Success [" + ret + "]");

					iError = fplib.CreateTemplate(fingerInfo, imageBuffer1, regMin2);
					if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {

						long nfiqvalue;
						long ret2 = fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1,
								quality);
						nfiqvalue = fplib.ComputeNFIQ(imageBuffer1, deviceInfo.imageWidth, deviceInfo.imageHeight);
						ret2 = fplib.GetNumOfMinutiae(SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400, regMin2,
								numOfMinutiae);
						if ((quality[0] >= MINIMUM_QUALITY) && (nfiqvalue <= MAXIMUM_NFIQ)
								&& (numOfMinutiae[0] >= MINIMUM_NUM_MINUTIAE)) {
							logger.info("Reg. Capture 2 PASS QC. Qual[" + quality[0] + "] NFIQ[" + nfiqvalue
									+ "] Minutiae[" + numOfMinutiae[0] + "]");
						} else {
							logger.info("Reg. Capture 2 FAIL QC. Quality[" + quality[0] + "] NFIQ[" + nfiqvalue
									+ "] Minutiae[" + numOfMinutiae[0] + "]");
						}

					} else
						logger.info("CreateTemplate() Error : " + iError);
				}
			} else
				logger.info("GetImageEx() Error : " + iError);
		//}
		return regMin2;
	}

	public byte[] captureR1() {
		//BaseResponse response = new BaseResponse();
		if (0 == deviceInfo.imageDPI) {
			init();
		}
		byte[] regMin1 = new byte[400];
		//if (response.getSuccess()) {
			int[] quality = new int[1];
			int[] numOfMinutiae = new int[1];
			byte[] imageBuffer1 = ((java.awt.image.DataBufferByte) imgRegistration1.getRaster().getDataBuffer())
					.getData();

			long iError = SGFDxErrorCode.SGFDX_ERROR_NONE;

			iError = fplib.GetImageEx(imageBuffer1, valSeconds * 1000, 0, valQuality);
			fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1, quality);
			// this.jProgressBarR1.setValue(quality[0]); calidad
			SGFingerInfo fingerInfo = new SGFingerInfo();
			fingerInfo.FingerNumber = SGFingerPosition.SG_FINGPOS_LI;
			fingerInfo.ImageQuality = quality[0];
			fingerInfo.ImpressionType = SGImpressionType.SG_IMPTYPE_LP;
			fingerInfo.ViewNumber = 1;

			if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
				// this.jLabelRegisterImage1.setIcon(new
				// ImageIcon(imgRegistration1.getScaledInstance(130,150,Image.SCALE_DEFAULT)));
				if (quality[0] < MINIMUM_QUALITY)
					logger.info("GetImageEx() Success [" + ret + "] but image quality is [" + quality[0]
							+ "]. Please try again");
				else {

					logger.info("GetImageEx() Success [" + ret + "]");

					iError = fplib.CreateTemplate(fingerInfo, imageBuffer1, regMin1);
					if (iError == SGFDxErrorCode.SGFDX_ERROR_NONE) {
						long nfiqvalue;
						long ret2 = fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1,
								quality);
						nfiqvalue = fplib.ComputeNFIQ(imageBuffer1, deviceInfo.imageWidth, deviceInfo.imageHeight);
						ret2 = fplib.GetNumOfMinutiae(SGFDxTemplateFormat.TEMPLATE_FORMAT_SG400, regMin1,
								numOfMinutiae);

						if ((quality[0] >= MINIMUM_QUALITY) && (nfiqvalue <= MAXIMUM_NFIQ)
								&& (numOfMinutiae[0] >= MINIMUM_NUM_MINUTIAE)) {
							logger.info("Reg. Capture 1 PASS QC. Qual[" + quality[0] + "] NFIQ[" + nfiqvalue
									+ "] Minutiae[" + numOfMinutiae[0] + "]");

						} else {
							logger.info("Reg. Capture 1 FAIL QC. Quality[" + quality[0] + "] NFIQ[" + nfiqvalue
									+ "] Minutiae[" + numOfMinutiae[0] + "]");

						}
					} else
						logger.info("CreateTemplate() Error : " + iError);
				}
			} else
				logger.info("GetImageEx() Error : " + iError);

		//}
		return regMin1;

	}

	public void capture() {
		//BaseResponse response = new BaseResponse();
		if (0 == deviceInfo.imageDPI) {
			init();
		}
		//if (response.getSuccess()) {
			int[] quality = new int[1];
			long nfiqvalue;
			BufferedImage img1gray = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight,
					BufferedImage.TYPE_BYTE_GRAY);
			byte[] imageBuffer1 = ((java.awt.image.DataBufferByte) img1gray.getRaster().getDataBuffer()).getData();
			if (fplib != null) {
				ret = fplib.GetImageEx(imageBuffer1, valSeconds * 1000, 0, valQuality);
				if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
					// this.jLabelImage.setIcon(new ImageIcon(img1gray));
					long ret2 = fplib.GetImageQuality(deviceInfo.imageWidth, deviceInfo.imageHeight, imageBuffer1,
							quality);
					nfiqvalue = fplib.ComputeNFIQ(imageBuffer1, deviceInfo.imageWidth, deviceInfo.imageHeight);
					logger.info("getImage() Success [" + ret + "] --- Image Quality [" + quality[0]
							+ "] --- NFIQ Value [" + nfiqvalue + "]");
				} else {
					logger.info("GetImageEx() Error [" + ret + "]");
				}
			} else {
				logger.info("JSGFPLib is not Initialized");
			}
		//}

	}

	private BaseResponse init() {		
		logger.info("PVI - init go");
		this.deviceName = SGFDxDeviceName.SG_DEV_AUTO;

		if (fplib != null) {
			fplib.CloseDevice();
			fplib.Close();
			fplib = null;
		}
		fplib = new JSGFPLib();
		ret = fplib.Init(this.deviceName);
		if ((fplib != null) && (ret == SGFDxErrorCode.SGFDX_ERROR_NONE)) {
			logger.info("JSGFPLib Initialization Success");

			this.devicePort = SGPPPortAddr.AUTO_DETECT;

			ret = fplib.OpenDevice(this.devicePort);

			if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
				logger.info("OpenDevice() Success [" + ret + "]");
				ret = fplib.GetDeviceInfo(deviceInfo);
				if (ret == SGFDxErrorCode.SGFDX_ERROR_NONE) {
					imgRegistration1 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight,
							BufferedImage.TYPE_BYTE_GRAY);
					imgRegistration2 = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight,
							BufferedImage.TYPE_BYTE_GRAY);
					imgVerification = new BufferedImage(deviceInfo.imageWidth, deviceInfo.imageHeight,
							BufferedImage.TYPE_BYTE_GRAY);
					responseBase.setSuccess(true);
				} else {
					logger.info("GetDeviceInfo() Error [" + ret + "]");
					messageResponse.setCode("005");
					messageResponse.setMessage("GetDeviceInfo() Error [" + ret + "]");
					responseBase.setMessage(messageResponse);
					responseBase.setSuccess(false);
				}
			} else {
				logger.info("OpenDevice() Error [" + ret + "]");
				messageResponse.setCode("006");
				messageResponse.setMessage("OpenDevice() Error [" + ret + "]");
				responseBase.setMessage(messageResponse);
				responseBase.setSuccess(false);
			}
		} else {
			logger.info("JSGFPLib Initialization Failed");
			messageResponse.setCode("007");
			messageResponse.setMessage("JSGFPLib Initialization Failed");
			responseBase.setMessage(messageResponse);
			responseBase.setSuccess(false);
		}

		return responseBase;
	}

}