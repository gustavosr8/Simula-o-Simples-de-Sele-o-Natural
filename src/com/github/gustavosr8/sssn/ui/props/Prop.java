package com.github.gustavosr8.sssn.ui.props;

public abstract class Prop {
	public abstract String getKey();
	public abstract String getValue();
	public abstract void setValue(String x) throws ErroProp;
}
