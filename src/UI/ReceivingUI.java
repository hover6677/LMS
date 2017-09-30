package UI;

public class ReceivingUI extends AbstractUI {
	protected ProcessingUI puiToBeDecorated;
	public ReceivingUI()
	{
		super("Receiving(Admin)","Template ID", "No. of Tags","Labels","Save");
		
		
	}
	@Override
	protected int getTotNum() {
		// TODO Auto-generated method stub
		return 8;
	}
	@Override
	void buttonAction() {
		// TODO Auto-generated method stub
		
	}
}
