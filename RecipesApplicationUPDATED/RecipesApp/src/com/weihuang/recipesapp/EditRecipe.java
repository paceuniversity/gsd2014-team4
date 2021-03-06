package com.weihuang.recipesapp;

import java.util.HashMap;

import com.weihuang.recipesapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditRecipe extends Activity{
	
	EditText recipeName;
	EditText recipeIngredients;
	EditText recipeInstruction;

	
	DBTools dbTools = new DBTools(this);
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_recipe);
		recipeName = (EditText) findViewById(R.id.recipeName);
		recipeIngredients = (EditText) findViewById(R.id.recipeIngredients);
		recipeInstruction = (EditText) findViewById(R.id.recipeInstruction);
		
		
		Intent theIntent = getIntent();
		
		String recipeId = theIntent.getStringExtra("recipeId");
		
		HashMap<String, String> recipeList = dbTools.getRecipeInfo(recipeId);
		
		if(recipeList.size() != 0){
			
			recipeName.setText(recipeList.get("recipeName"));
			recipeIngredients.setText(recipeList.get("recipeIngredients"));
			recipeInstruction.setText(recipeList.get("recipeInstruction"));
		
			
		}
	}
	
	public void editContact(View view){
		
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		recipeName = (EditText) findViewById(R.id.recipeName);
		recipeIngredients = (EditText) findViewById(R.id.recipeIngredients);
		recipeInstruction = (EditText) findViewById(R.id.recipeInstruction);
		
		Intent theIntent = getIntent();
		
		String recipeId = theIntent.getStringExtra("recipeId");
		
		queryValuesMap.put("recipeId", recipeId);
		queryValuesMap.put("recipeName", recipeName.getText().toString());
		queryValuesMap.put("recipeIngredients", recipeIngredients.getText().toString());
		queryValuesMap.put("recipeInstruction", recipeInstruction.getText().toString());
	
		
		dbTools.updateRecipe(queryValuesMap);
		this.callMainActivity(view);
		
	}
	
	public void removeRecipe(View view){
		
		Intent theIntent = getIntent();
		
		String recipeId = theIntent.getStringExtra("recipeId");
		
		dbTools.deleteRecipe(recipeId);
		
		this.callMainActivity(view);
		
	}
	
	public void callMainActivity(View view){
		
		Intent objIntent = new Intent(getApplication(), MainActivity.class);
		
		startActivity(objIntent);
		
	}
	
}






