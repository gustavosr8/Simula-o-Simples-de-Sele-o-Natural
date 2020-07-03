package com.github.gustavosr8.sssn.ui.props;

public class PropBoolean extends Prop {
	private String mKey;
	private boolean mVal;

	public PropBoolean(String key, boolean val) {
		mKey = key;
		mVal = val;
	}
	
	public boolean get() {
		return mVal;
	}
	
	@Override
	public String getKey() {
		return mKey;
	}

	@Override
	public String getValue() {
		return Boolean.toString(mVal);
	}

	@Override
	public void setValue(String x) throws ErroProp {
		try {
			mVal = Boolean.parseBoolean(x);
		} catch (NumberFormatException e) {
			throw new ErroPropTipoInvalido("A propriedade deve ser true/false");
		}
	}
}
