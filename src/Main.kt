import kotlin.random.Random

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    MobilePhone("12").apply {
        val dio = Contact("Dio Momber", "1")
        val jojo = Contact("Jojo Jostar", "0")
        addNewContact(dio)
        printContacts()
        updateContact(dio, jojo, UpdateType.All)
        printContacts()

        // Change Dio's phone number to "3"
//        val newDio = Contact("Dio Momber", "3")
//        updateContact(dio, newDio, UpdateType.Phone)
//        printContacts()


        updateContact(jojo, dio, UpdateType.Name)
        printContacts()


        printContacts()

        queryContact(dio.name)?.let(::println)
        findContact(jojo).let(::println)
        removeContact(jojo)
        printContacts()
        val leha = Contact("Arbuz", "1234")
        addNewContact(leha)
        printContacts()
        updateContact(leha.copy(name = "Lom"))
        printContacts()
    }
    val newTree = Node(3).apply {
        insert(6)
        insert(5)
        insert(2)
        insert(1)
        insert(4)
        print(this)
    }
}

class MobilePhone(myNumber: String) {
    private val myContacts = mutableListOf(Contact("Marusa", myNumber))

    fun addNewContact(newContact: Contact) = myContacts.add(newContact)

    fun updateContact(oldContact: Contact, newContact: Contact, type: UpdateType): Boolean {
        var isUpdated = false
        when (type) {
            UpdateType.All -> {
                isUpdated = myContacts.removeIf { it == oldContact }
                if (isUpdated) {
                    myContacts.add(newContact)
                }
            }
            UpdateType.Phone -> {
                val currentContact = queryContact(oldContact.name)
                if (currentContact != null) {
                    currentContact.number = newContact.number
                    isUpdated = true
                }
            }
            UpdateType.Name -> {
                val currentContact = queryContact(oldContact.name)
                if (currentContact != null) {
                    currentContact.name = newContact.name
                    isUpdated = true
                }
            }
        }
        return isUpdated
    }

    fun updateContact(newContact: Contact) {
        myContacts.replaceAll {
            if (it.id == newContact.id) newContact else it
        }
    }

    fun removeContact(contact: Contact) = myContacts.remove(contact)

    fun findContact(contact: Contact): Int = myContacts.indexOf(contact)

    fun queryContact(query: String): Contact? = myContacts.firstOrNull { it.name == query }

    fun printContacts() {
        myContacts.forEach {
            println(it)
            println('\n')
        }
        println("===================")
    }
}

enum class UpdateType {
    All, Phone, Name
}

data class Contact(var name: String, var number: String, val id: Int = Random.nextInt())