package com.contatolist.controllers

import com.contatolist.entities.Contact
import com.contatolist.repository.ContactRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/contact")
class ContactController {

    @Autowired
    lateinit var repository: ContactRepository

    // Pucha a lista de contato
    @GetMapping
    fun contacts(): List<Contact> {
        return repository.findAll()

    }

    // Adiciona um contato
    @PostMapping
    fun create(@Valid @RequestBody contact: Contact): Contact {
        return repository.save(contact)

    }

    //Adiciona um Id unico para cada contato
    @GetMapping
    fun show(@PathVariable("id") id: Long): Contact{
        return repository.findById(id).orElseThrow{EntityNotFoundException()}
    }

    // Altera as informações do usuario
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody  newContact: Contact): Contact {

        val contact = repository.findById(id).orElseThrow{EntityNotFoundException()}

        contact.apply {
            this.name = newContact.name
            this.email = newContact.email
        }
        return repository.save(contact)

    }

    // Apaga o contato
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long){

        val contact = repository.findById(id).orElseThrow{ EntityNotFoundException() }

        repository.delete(contact)

    }
}








