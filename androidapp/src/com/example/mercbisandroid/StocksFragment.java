package com.example.mercbisandroid;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author Rickard Bremer
 * This fragment displays a List of all possible stocks.
 * 
 */

public class StocksFragment extends ListFragment implements OnItemClickListener {
	
	// Rickard Bremer

	JSONObject JOBJ = new JSONObject();
	String[] STOCKNAME, STOCKSYMBOL, STOCKMARKET;

	public ArrayList<JSONObject> STOCKLIST = new ArrayList<JSONObject>();

	public StocksFragment() {
		// Required empty public constructor
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		// Rickard Bremer

		try {
			STOCKNAME = new String[MainActivity.stockArray.get().size()];
			STOCKSYMBOL = new String[MainActivity.stockArray.get().size()];
			STOCKMARKET = new String[MainActivity.stockArray.get().size()];

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			e1.printStackTrace();
		}

		try {
			for (int i = 0; i < MainActivity.stockArray.get().size(); i++) {
				String JsonLine = MainActivity.stockArray.get().get(i)
						.toString();

				try {

					JOBJ = new JSONObject(JsonLine);
					STOCKLIST.add(JOBJ);

					STOCKNAME[i] = STOCKLIST.get(i).getString("name");
					STOCKSYMBOL[i] = STOCKLIST.get(i).getString("symbol");
					STOCKMARKET[i] = STOCKLIST.get(i).getString("market");

				} catch (JSONException e) {

					e.printStackTrace();
				}

			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.list_stocks, STOCKNAME));

		ListView listView = getListView(); // EX:
		listView.setTextFilterEnabled(true);

		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// Rickard Bremer
				MainActivity.stockPos = position;
				// System.out.println(STOCKLIST.get(position));
				MainActivity.StockObject = STOCKLIST.get(position);

				Intent StockActivity = new Intent(getActivity(), StockActivity.class);
				MainActivity.StockSymbol = STOCKSYMBOL[position];
				MainActivity.StockName = STOCKNAME[position];
				MainActivity.StockMarket = STOCKMARKET[position];

				startActivity(StockActivity);
			}
		});

		registerForContextMenu(listView);
		super.onActivityCreated(savedInstanceState);
		return;

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		System.out.println("Hello World");

	}

}
