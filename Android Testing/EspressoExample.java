  
   
----------------------------Activity Scenario---------------------------------------------
   @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
	
	
	
----------------------------Init check---------------------------------------------

	onView(withId(R.id.logoPicto)).check(matches(isDisplayed()))
	onView(withId(R.id.navigation_host_bottom_sheet_behavior)).check(matches(isDisplayed()))
	Thread.sleep(4000)
		
	
	private fun environmentVariableSet() {
        onView(withText("PREPROD")).check(matches(isDisplayed())).perform(click())
        onView(withText("EMEA")).check(matches(isDisplayed())).perform(click())
        onView(withText("APP")).check(matches(isDisplayed())).perform(click())

        onView(withId(R.id.brandRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.brandRecyclerView)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(6))
        onView(withId(R.id.brandRecyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                6,
                click()
            )
        )
    }
	
	
	
	
	
----------------------------RecyclerView---------------------------------------------
	
	onView(withId(R.id.brandRecyclerView)).check(matches(isDisplayed()))
	onView(withId(R.id.brandRecyclerView)).perform(RecyclerViewActions.scrollToPosition<ViewHolder>(10))
	onView(withId(R.id.brandRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(10,click ()))




	
-----------------------------------Login---------input field value---------------------------

 private fun loginPageScenario() {
        try {
            onView(withId(R.id.authentication_button_signin)).perform(click())


            getInstrumentation().waitForIdleSync();
            onView(withId(R.id.email)).check(matches(isDisplayed()))
            onView(withId(R.id.signInLabel))
            onView(withId(R.id.signInLabel)).check(matches(isDisplayed()))

            activityRule.scenario.onActivity { activity ->
                // onView(withId(R.id.email)).perform(click()).perform(pressKey(KeyEvent.KEYCODE_A))
                val emaild = activity.findViewById<SFTextInput>(R.id.email)
                val password = activity.findViewById<SFTextInput>(R.id.password)
                emaild.editText.setText("drm10_15@yopmail.com")
                password.editText.setText("Password04_")

                Thread.sleep(100)

                activity.findViewById<SFButton>(R.id.authentication_button_continue).performClick()

                Thread.sleep(500)
            }
        } catch (e: AssertionFailedError) {
            e.printStackTrace()
        }
    }
		
		
		
		

		
-----------------------------------Language---------Selections---------------------------

	activityRule.scenario.onActivity { activity ->
			val recucleview = activity.findViewById<RecyclerView>(R.id.languageItems)
			val adapter = recucleview.adapter as LanguageAdapter
			val itemCount = recucleview.adapter?.itemCount?:0
			val selc = adapter.tracker?.selection
			selc?.let { adapter.tracker?.setItemsSelected(it, true) }
			Log.e("Language getItemDetails", "start getItemDetails itemCount: $itemCount")
	}

	
	
	//SelectLanguageViewModel.kt
	  @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
     internal fun onTrackerSelectionChanged() {
        /*
        val selectionItemx = defaultLanguageItem
                if (selectionItemx != null) {
                    Log.e("Language getItemDetails", "getItemDetails 1")
                    handleSelectedLanguageClick(item = selectionItemx)
                }else{
                    Log.e("Language getItemDetails", "getItemDetails 2")
                }
         */
        val tracker = isTrackerInitialised() ?: return
        if (isSearchingLanguage) {
            return
        }
         // tracker.selection.forEach {
        val selectionItem = defaultLanguageItem as LanguageItem
        if (selectionItem.isSelected) {
            isDialogShown = true
            handleSelectedLanguageClick(item = selectionItem)
         } else {
             isDialogShown = false
         }
      //  }
    }
	
	
	//LanguageAdapter.kt : remove internal access modifire to access it from testing class
    internal var tracker: SelectionTracker<ListItemData>? = null
        set(value) {
            field = value
            field?.addObserver(object : SelectionObserver<ListItemData>() {
                override fun onSelectionChanged() {
                    super.onSelectionChanged()
                    val selection = tracker?.hasSelection() ?: false
                    if (selectionMode.get() != selection) {
                        selectionMode.set(selection)
                    }
                }
            })
        }
		
		


		
-----------------------------------Language Selection---------Popup Event---------------------------

    private var decorView: View? = null


		activityRule.scenario.onActivity { activity: MainActivity ->
				decorView = activity.window.decorView
				val hasWindowFocus = activity.hasWindowFocus()
				Log.e("Language hasWindowFocus", "hasWindowFocus: $hasWindowFocus    decorView:${decorView}")
			}
		onView(withText("Cancel")).
		inRoot(RootMatchers.withDecorView(not(decorView)))
			.check(matches(isDisplayed())).perform(click())		
			
			






-----------------------------------Buttom Navigation---------------------------

private fun buttomNavigationClick(){
        //mainBottomNavigationView
        //onView(withText("Trips")).perform(click())
        val bottomNavigationItemView = onView(
            Matchers.allOf(
                withId(id.trips), ViewMatchers.withContentDescription("Trips"),
                childAtPosition(
                    childAtPosition(
                        withId(id.mainBottomNavigationView),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
    }


    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
	
	
	
	

-----------------------------------Profile Icon Click---------------------------

	 private fun tabClickOnProfileIcon(){
        activityRule.scenario.onActivity { activity ->
            val profileicon = activity.findViewById<SFAvatar>(R.id.tgAvatar)
            profileicon.performClick()
        }
    }





-----------------------------------DashBoard Card Click---------------------------

    private fun dashBoardCardClick(){
        onView(withId(R.id.cardsList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(
                1,
                click()
            )
        )
    }
