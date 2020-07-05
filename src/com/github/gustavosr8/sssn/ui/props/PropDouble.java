package com.github.gustavosr8.sssn.ui.props;

import java.text.DecimalFormat;
import java.text.ParseException;

public class PropDouble extends Prop {
	private static DecimalFormat df = new DecimalFormat("0.0#######");
	
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
		mVal = d;
	}

	@Override
	public String getKey() {
		return mKey;
	}

	@Override
	public String getValue() {
		return df.format(mVal);
	}

	@Override
	public void setValue(String x) throws ErroProp {
		try {
			double y = df.parse(x).doubleValue();
			if (y >= mMin && y <= mMax)
				mVal = y;
			else
				throw new ErroPropForaDeAlcance("A propriedade deve estar entre " + mMin + " e " + mMax);
		} catch (ParseException e) {
			throw new ErroPropTipoInvalido("A propriedade deve ser um número");
		}
	}
}
