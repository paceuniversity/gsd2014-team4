package com.weihuang.recipesapp;

import java.util.ArrayList;
import java.util.HashMap;

import com.weihuang.recipesapp.R;
import com.weihuang.recipesapp.DBTools;
import com.weihuang.recipesapp.NewRecipe;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;


public class MainActivity extends ListActivity {

	// The Intent is used to issue that an operation should
	// be performed

	Intent intent;
	TextView recipeId;
	
	// The object that allows me to manipulate the database
	
	DBTools dbTools = new DBTools(this);
	
	// The object that allows me to manipulate the database

	protected void onCreate(Bundle savedInstanceState) {
		
		// Get saved data if there is any
	
		super.onCreate(savedInstanceState);
		
		// Designate that edit_recipe.xml is the interface used
		// is activity_main.xml
		
		setContentView(R.layout.activity_main);
		
		// Gets all the data from the database and stores it
		// in an ArrayList
		
		ArrayList<HashMap<String, String>> recipeList =  dbTools.getAllrecipes();

		// Check to make sure there are recipes to display
		
		if(recipeList.size()!=0) {
			
			// Get the ListView and assign an event handler to it
				
			ListView listView = getListView();
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				// When an item is clicked get the TextView
				// with a matching checkId
				
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					
					// Convert that recipeId into a String
					
					recipeId = (TextView) view.findViewById(R.id.recipeId);
						
					String recipeIdValue = recipeId.getText().toString();	
					
					// Signals an intention to do something
					// getApplication() returns the application that owns
					// this activity
					
					Intent  theIndent = new Intent(getApplication(),EditRecipe.class);
					
					// Put additional data in for Edit to use
					
					theIndent.putExtra("recipeId", recipeIdValue); 
					
					// Calls for Edit
					
					startActivity(theIndent); 
				}
			}); 
			
			// A list adapter is used bridge between a ListView and
			// the ListViews data
			
			// The SimpleAdapter connects the data in an ArrayList
			// to the XML file
			
			// First we pass in a Context to provide information needed
			// about the application
			// The ArrayList of data is next followed by the XML resource
			// Then we have the names of the data in String format and
			// their specific resource IDS
			
			ListAdapter adapter = new SimpleAdapter( MainActivity.this,recipeList, R.layout.recipe_entry, new String[] { "recipeId","recipeName"}, new int[] {R.id.recipeId, R.id.recipeName});
			
			// setListAdapter provides the Cursor for the ListView
			// The Cursor provides access to the database data
			
			setListAdapter(adapter);
		}
	}
	
	// When showAddRecipe is called with a click the Activity 
	// NewRecipe is called
	
	public void showAddRecipe(View view) {
		Intent theIntent = new Intent(getApplicationContext(), NewRecipe.class);
		startActivity(theIntent);
	}
}
