package com.github.gustavosr8.sssn.ui.props;

public class PropDouble extends Prop {
	private String mKey;
	private double mVal;
	private double mMin;
	private double mMax;
	
	public PropDouble(String key, double val, double min, double max) {
		mKey = key;
		mVal = val;
		mMin = min;
		mMax = max;
	}
	
	public double get() {
		return mVal;
	}
	
	public void set(double d) {
		mVal=d;
	}
	
	@Override
	public String getKey() {
		return mKey;
	}

	@Override
	public String getValue() {
		return Double.toString(mVal);
	}

	@Override
	public void setValue(String x) throws ErroProp {
		try {
			double y = Double.parseDouble(x);
			if (y >= mMin && y <= mMax)
				mVal = y;
			else
				throw new ErroPropForaDeAlcance("A propriedade deve estar entre " + mMin + " e " + mMax);
		} catch (NumberFormatException e) {
			throw new ErroPropTipoInvalido("A propriedade deve ser um número");
		}
	}
}
