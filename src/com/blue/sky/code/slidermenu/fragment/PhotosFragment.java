package com.blue.sky.code.slidermenu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.Toast;
import com.blue.sky.component.R;

public class PhotosFragment extends Fragment {
	
	public PhotosFragment(){}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		setHasOptionsMenu(true);
		
		super.onCreate(savedInstanceState);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.navigation_fragment_photos, container, false);
        
        return rootView;
    }
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.photo, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_share:
            
            Toast.makeText(getActivity(), R.string.action_share, Toast.LENGTH_SHORT).show();
            
            return true;
            default:
            	return super.onOptionsItemSelected(item);
        }
	}
}
