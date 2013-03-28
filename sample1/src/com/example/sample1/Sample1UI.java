package com.example.sample1;

import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.Action;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

/* 
 * UI class is the starting point for your app. You may deploy it with VaadinServlet
 * or VaadinPortlet by giving your UI class name a parameter. When you browse to your
 * app a web page showing your UI is automatically generated. Or you may choose to 
 * embed your UI to an existing web page. 
 */
//@Theme("sampletheme")
@Theme("sample1theme")
@Title("SampleUI")
public class Sample1UI extends UI {

	/* User interface components are stored in session. */
	private ICEPush pusher = new ICEPush();
	private Table contactList = new Table();
	private TextField searchField = new TextField();
	private Button addNewContactButton = new Button("New");
	private Window window;
	private Button removeContactButton = new Button("Remove this contact");
	private FormLayout editorLayout = new FormLayout();
	private FieldGroup editorFields = new FieldGroup();
	static final Action Option1 = new Action("Trading Window");
	static final Action Option2 = new Action("Remove");
	static final Action[] ITEM_ACTIONS = new Action[] { Option1,Option2 };
	private static final String LINE_NO = "LINE NO";
	private static final String FNAME = "First Name";
	private static final String LNAME = "Last Name";
	private static final String COMPANY = "Company";
	private static final String[] fieldNames = new String[] {LINE_NO, FNAME, LNAME,COMPANY};
	
	//tickerFlasher.start();
	/*
	 * Any component can be bound to an external data source. This example uses
	 * just a dummy in-memory list, but there are many more practical
	 * implementations.
	 */
	IndexedContainer ic = new IndexedContainer();
	Updater updater = new Updater();
	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	protected void init(VaadinRequest request) {
		initLayout();
		initContactList();
		initEditor();
		//initSearch();
		//initAddRemoveButtons();
	}

	/*
	 * In this example layouts are programmed in Java. You may choose use a
	 * visual editor, CSS or HTML templates for layout instead.
	 */
	private void initLayout() {

		/* Root of the user interface component tree is set */
		//HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		//setContent(splitPanel);

		/* Build the component tree */
		VerticalLayout layout = new VerticalLayout();
		//splitPanel.addComponent(leftLayout);
		//splitPanel.addComponent(editorLayout);
		setContent(layout);
		///layout.addComponent(pusher);
		layout.addComponent(contactList);
		//HorizontalLayout bottomLeftLayout = new HorizontalLayout();
		//leftLayout.addComponent(bottomLeftLayout);
		//bottomLeftLayout.addComponent(searchField);
		//bottomLeftLayout.addComponent(addNewContactButton);

		/* Set the contents in the left of the split panel to use all the space */
		layout.setSizeFull();

		/*
		 * On the left side, expand the size of the contactList so that it uses
		 * all the space left after from bottomLeftLayout
		 */
		layout.setExpandRatio(contactList, 1);
		contactList.setSizeFull();
		
		addExtension(pusher);
 
		/*
		 * In the bottomLeftLayout, searchField takes all the width there is
		 * after adding addNewContactButton. The height of the layout is defined
		 * by the tallest component.
		 */
		//bottomLeftLayout.setWidth("100%");
		//searchField.setWidth("100%");
		//bottomLeftLayout.setExpandRatio(searchField, 1);

		/* Put a little margin around the fields in the right side editor */
		//editorLayout.setMargin(true);
		//editorLayout.setVisible(false);
	}

	private void initEditor() {


		/* User interface can be created dynamically to reflect underlying data. */
		for (String fieldName : fieldNames) {
			TextField field = new TextField(fieldName);
			editorLayout.addComponent(field);
			field.setWidth("50%");
			//CssLayout c
			/*
			 * We use a FieldGroup to connect multiple components to a data
			 * source at once.
			 */
			editorFields.bind(field, fieldName);
			//editorFields.se
		}
		Button save = new Button("Save");
		save.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                // Close the sub-window
                window.close();
            }
        });
        editorLayout.addComponent(save);

		/*
		 * Data can be buffered in the user interface. When doing so, commit()
		 * writes the changes to the data source. Here we choose to write the
		 * changes automatically without calling commit().
		 */
		editorFields.setBuffered(false);
	}

/*	private void initSearch() {

		
		 * We want to show a subtle prompt in the search field. We could also
		 * set a caption that would be shown above the field or description to
		 * be shown in a tooltip.
		 
		searchField.setInputPrompt("Search contacts");

		
		 * Granularity for sending events over the wire can be controlled. By
		 * default simple changes like writing a text in TextField are sent to
		 * server with the next Ajax call. You can set your component to be
		 * immediate to send the changes to server immediately after focus
		 * leaves the field. Here we choose to send the text over the wire as
		 * soon as user stops writing for a moment.
		 
		searchField.setTextChangeEventMode(TextChangeEventMode.LAZY);

		
		 * When the event happens, we handle it in the anonymous inner class.
		 * You may choose to use separate controllers (in MVC) or presenters (in
		 * MVP) instead. In the end, the preferred application architecture is
		 * up to you.
		 
		searchField.addTextChangeListener(new TextChangeListener() {
			public void textChange(final TextChangeEvent event) {

				 Reset the filter for the contactContainer. 
				contactContainer.removeAllContainerFilters();
				contactContainer.addContainerFilter(new ContactFilter(event
						.getText()));
			}
		});
	}
*/
	/*
	 * A custom filter for searching names and companies in the
	 * contactContainer.
	 
	private class ContactFilter implements Filter {
		private String needle;

		public ContactFilter(String needle) {
			this.needle = needle.toLowerCase();
		}

		public boolean passesFilter(Object itemId, Item item) {
			String haystack = ("" + item.getItemProperty(FNAME).getValue()
					+ item.getItemProperty(LNAME).getValue() + item
					.getItemProperty(COMPANY).getValue()).toLowerCase();
			return haystack.contains(needle);
		}

		public boolean appliesToProperty(Object id) {
			return true;
		}
	}*/

	/*private void initAddRemoveButtons() {
		addNewContactButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {

				
				 * Rows in the Container data model are called Item. Here we add
				 * a new row in the beginning of the list.
				 
				contactContainer.removeAllContainerFilters();
				Object contactId = contactContainer.addItemAt(0);

				
				 * Each Item has a set of Properties that hold values. Here we
				 * set a couple of those.
				 
				contactList.getContainerProperty(contactId, FNAME).setValue(
						"New");
				contactList.getContainerProperty(contactId, LNAME).setValue(
						"Contact");

				 Lets choose the newly created contact to edit it. 
				contactList.select(contactId);
			}
		});

		removeContactButton.addClickListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				Object contactId = contactList.getValue();
				contactList.removeItem(contactId);
			}
		});
	}*/
	public class Updater extends Thread {
		 public Updater() {
			// TODO Auto-generated constructor stub
			 for (String p : fieldNames) {
					ic.addContainerProperty(p, String.class, "");
				}
			 start();
		}
		@Override
		public void run() {
			int count = 0;
			
			 while(true){
				 try {
					// count++;
						/* Create dummy data by randomly combining first and last names */
					 System.out.println("in--"+count);
						String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
								"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
								"Lisa", "Marge" };
						String[] lnames = { "Smith", "Gordon", "Simpson", "Brown", "Clavel",
								"Simons", "Verne", "Scott", "Allison", "Gates", "Rowling",
								"Barks", "Ross", "Schneider", "Tate" };
						//for (int i = 0; i < 20; i++) {
							Object id = ic.addItem();
							ic.getContainerProperty(id, LINE_NO).setValue(""+(count+1));
							ic.getContainerProperty(id, FNAME).setValue(fnames[(int) (fnames.length * Math.random())]);
							ic.getContainerProperty(id, LNAME).setValue(lnames[(int) (lnames.length * Math.random())]);
							ic.getContainerProperty(id, COMPANY).setValue("Company"+count);
						//}
						count++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 
				 try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pusher.push();
			 }
		}

	}
	private void initContactList() {
		
		contactList.setContainerDataSource(ic);
		contactList.setVisibleColumns(new String[] { LINE_NO,FNAME, LNAME, COMPANY });
		contactList.setSelectable(true);
		contactList.setImmediate(true);
		contactList.setColumnReorderingAllowed(true);
		contactList.setColumnCollapsingAllowed(true);
		
		// Set cell style generator
		contactList.setCellStyleGenerator(new Table.CellStyleGenerator() {
		    public String getStyle(Table contactList, Object itemId, Object propertyId) {
		        // Row style setting, not relevant in this example.
		    	//System.out.println(propertyId +"----"+itemId +"---" + contactList.getItem(itemId));
		    	if (propertyId == null)
		            return "green"; // Will not actually be visible

		        int row = ((Integer)itemId).intValue();
		        
		        // The first column.
		        if (propertyId.equals("LINE NO"))
		            return "rowheader";
		        
		        // Other cells.
		       String value = (String) contactList.getItem(itemId).getItemProperty("First Name").getValue();
		       //System.out.println(val.getValue());
		        System.out.println(value.equals("Mike"));
		        if (value.equals("Mike")  && propertyId.equals("First Name") )
		            return "black";
		        else
		            return "white";
		    }
		});
		//contactList.setCellStyleGenerator(cellStyleGenerator);
		/*contactList.addValueChangeListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Object contactId = contactList.getValue();

				
				 * When a contact is selected from the list, we want to show
				 * that in our editor on the right. This is nicely done by the
				 * FieldGroup that binds all the fields to the corresponding
				 * Properties in our contact at once.
				 
				if (contactId != null)
					editorFields.setItemDataSource(contactList
							.getItem(contactId));
				
				editorLayout.setVisible(contactId != null);
			}
		});*/
		 
		/*contactList.addListener(new ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				if (event.getButton() == ItemClickEvent.BUTTON_RIGHT) {
					contactList.select(event.getItemId());
				}
			}
		});*/

		contactList.addActionHandler(new Action.Handler() {
			public Action[] getActions(Object target, Object sender) {
				return ITEM_ACTIONS;
			}

			public void handleAction(Action action, Object sender, Object target) {
				Item rowItem = contactList.getItem(target);

				System.err.println(rowItem + ":::" + action.getCaption());
				if(!action.getCaption().equals("Remove")){
						window = new Window(action.getCaption());
						window.setContent(editorLayout);
						window.setWidth("400px");
						window.setHeight("400px");
						window.center();
						Object contactId = contactList.getValue();
						 
						if (contactId != null)
							editorFields.setItemDataSource(contactList
									.getItem(contactId));
						
						editorLayout.setVisible(contactId != null);
						UI.getCurrent().addWindow(window);
				}else{
					Object contactId = contactList.getValue();
					contactList.removeItem(contactId);
				}
			
			}
		});
	}

	/*
	 * Generate some in-memory example data to play with. In a real application
	 * we could be using SQLContainer, JPAContainer or some other to persist the
	 * data.
	 */
	private static IndexedContainer createDummyDatasource() {
		IndexedContainer ic = new IndexedContainer();

		for (String p : fieldNames) {
			ic.addContainerProperty(p, String.class, "");
		}

		/* Create dummy data by randomly combining first and last names */
		String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
				"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
				"Lisa", "Marge" };
		String[] lnames = { "Smith", "Gordon", "Simpson", "Brown", "Clavel",
				"Simons", "Verne", "Scott", "Allison", "Gates", "Rowling",
				"Barks", "Ross", "Schneider", "Tate" };
		for (int i = 0; i < 20; i++) {
			Object id = ic.addItem();
			ic.getContainerProperty(id, LINE_NO).setValue(""+(i+1));
			ic.getContainerProperty(id, FNAME).setValue(fnames[(int) (fnames.length * Math.random())]);
			ic.getContainerProperty(id, LNAME).setValue(lnames[(int) (lnames.length * Math.random())]);
			ic.getContainerProperty(id, COMPANY).setValue("Company"+i);
		}

		return ic;
	}
	

}
