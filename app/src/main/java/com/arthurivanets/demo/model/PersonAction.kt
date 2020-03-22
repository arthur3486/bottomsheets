package com.arthurivanets.demo.model

import com.arthurivanets.bottomsheets.sheets2.Action

internal class PersonAction(val person : Person) : Action {


    override val id = person.id


}