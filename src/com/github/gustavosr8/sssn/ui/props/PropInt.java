package com.github.gustavosr8.sssn.ui.props;

public class PropInt extends Prop {
	private String mKey;
	private int mVal;
	private int mMin;
	private int mMax;
	
	public PropInt(String key, int val, int min, int max) {
		mKey = key;
		mVal = val;
		mMin = min;
		mMax = max;
	}
	
	public int get() {
		return mVal;
	}
	
	@Override
	public String getKey() {
		return mKey;
	}

	@Override
	public String getValue() {
		return Integer.toString(mVal);
	}

	@Override
	public void setValue(String x) throws ErroProp {
		try {
			double y = Integer.parseInt(x);
			if (y >= mMin && y <= mMax)
				mVal = y;
			else
				throw new ErroPropForaDeAlcance("A propriedade deve estar entre " + mMin + " e " + mMax);
		} catch (NumberFormatException e) {
			throw new ErroPropTipoInvalido("A propriedade deve ser um inteiro");
		}
	}
}
