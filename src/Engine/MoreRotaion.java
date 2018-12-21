package Engine;

public enum MoreRotaion {
	NONE, ROTATE_45, ROTATE_90, ROTATE_135, ROTATE_180, ROTATE_225, ROTATE_270, ROTATE_315;

	  public double getRadians() {
	    int value;
	    switch (this) {
	    	case ROTATE_45: 
		      value = 45;
		      break;
		    case ROTATE_90:
		      value = 90;
		      break;
		    case ROTATE_135:
		      value = 135;
		      break;
		    case ROTATE_180:
		      value = 180;
		      break;
		    case ROTATE_225:
		      value = 225;
		      break;
		    case ROTATE_270:
		      value = 270;
		      break;
		    case ROTATE_315:
		      value = 315;
		      break;
		    default:
		      value = 0;
		      break;
	    }

	    return Math.toRadians(value);
	  }

}
