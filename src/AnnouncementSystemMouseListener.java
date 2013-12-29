import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class AnnouncementSystemMouseListener implements MouseListener, MouseMotionListener {

	public static final int POINT_QUERY_BUTTON =0;
	public static final int RANGE_QUERY_BUTTON =1;
	public static final int SURROUNDING_QUERY_BUTTON =2;
	public static final int EMERGENCY_QUERY_BUTTON =3;
	
	private int x = -1;
	private int y = -1;
	private int radioButtonSelected;
	private boolean isRightClick;
	
	AnnouncementSystemMouseListener(){
		setRadioButtonSelected(-1);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(radioButtonSelected == -1)
			return;
		if(e.getButton() == 1){
			isRightClick = false;
		}
		else if(e.getButton() == 3){
			isRightClick = true;
		}
		else return;
		x = e.getX();
		y = e.getY();
		HW2.label.repaint();
		//AnnouncementSystemFrame.mousePositionField.setText("Clicked at: " + getX() + "," + getY());
		System.out.println("Clicked" + getX() + "," + getY());
	}
	

	public boolean isRightClick() {
		return isRightClick;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	int getX() {
		return x;
	}
	void resetClick() {
		x = -1;
		y = -1;
		isRightClick = false;
		
	}
	int getY() {
		return y;
	}
	public int getRadioButtonSelected() {
		return radioButtonSelected;
	}
	public void setRadioButtonSelected(int radioButtonSelected) {
		this.radioButtonSelected = radioButtonSelected;
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		HW2.mousePositionField.setText(e.getX() + "," + e.getY());
		
	}
	

}
