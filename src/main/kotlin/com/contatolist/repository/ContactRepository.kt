package com.contatolist.repository

import com.contatolist.entities.Contact
import org.springframework.data.jpa.repository.JpaRepository


interface ContactRepository: JpaRepository<Contact, Long> {
}