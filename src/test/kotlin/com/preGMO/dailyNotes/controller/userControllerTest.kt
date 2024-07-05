package com.pre_gmo.daily_notes.controller

import com.pre_gmo.daily_notes.UserDTO
import com.pre_gmo.daily_notes.model.User
import com.pre_gmo.daily_notes.service.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@WebMvcTest(UserController::class)
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    private lateinit var user: User
    private lateinit var userDTO: UserDTO

    @BeforeEach
    fun setUp() {
        user = User(id = 1L, name = "John Doe", email = "john.doe@example.com")
        userDTO = UserDTO(name = "John Doe", email = "john.doe@example.com")
    }

    @Test
    fun `should get all users`() {
        `when`(userService.findAll()).thenReturn(listOf(user))

        mockMvc
            .perform(get("/api/user"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(user.id))
            .andExpect(jsonPath("$[0].name").value(user.name))
            .andExpect(jsonPath("$[0].email").value(user.email))

        verify(userService, times(1)).findAll()
    }

    @Test
    fun `should get user by id`() {
        `when`(userService.findById(user.id)).thenReturn(Optional.of(user))

        mockMvc
            .perform(get("/api/user/{id}", user.id))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(user.id))
            .andExpect(jsonPath("$.name").value(user.name))
            .andExpect(jsonPath("$.email").value(user.email))

        verify(userService, times(1)).findById(user.id)
    }

    @Test
    fun `should return 404 when user not found`() {
        `when`(userService.findById(2L)).thenReturn(Optional.empty())

        mockMvc
            .perform(get("/api/user/{id}", 2L))
            .andExpect(status().isNotFound)

        verify(userService, times(1)).findById(2L)
    }

    @Test
    fun `should create user`() {
        `when`(userService.create(userDTO)).thenReturn(user)

        mockMvc
            .perform(
                post("/api/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                        {
                            "name": "${userDTO.name}",
                            "email": "${userDTO.email}"
                        }
                        """.trimIndent(),
                    ),
            ).andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(user.id))
            .andExpect(jsonPath("$.name").value(user.name))
            .andExpect(jsonPath("$.email").value(user.email))

        verify(userService, times(1)).create(userDTO)
    }

    @Test
    fun `should update user`() {
        val updatedUser = user.copy(name = "Jane Doe", email = "jane.doe@example.com")
        val updatedUserDTO = UserDTO(name = "Jane Doe", email = "jane.doe@example.com")

        `when`(userService.findById(user.id)).thenReturn(Optional.of(user))
        `when`(userService.update(any())).thenReturn(updatedUser)

        mockMvc
            .perform(
                put("/api/user/{id}", user.id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                        {
                            "name": "${updatedUserDTO.name}",
                            "email": "${updatedUserDTO.email}"
                        }
                        """.trimIndent(),
                    ),
            ).andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(updatedUser.id))
            .andExpect(jsonPath("$.name").value(updatedUser.name))
            .andExpect(jsonPath("$.email").value(updatedUser.email))

        verify(userService, times(1)).findById(user.id)
        verify(userService, times(1)).update(any())
    }

    @Test
    fun `should delete user`() {
        `when`(userService.findById(user.id)).thenReturn(Optional.of(user))

        mockMvc
            .perform(delete("/api/user/{id}", user.id))
            .andExpect(status().isOk)

        verify(userService, times(1)).findById(user.id)
        verify(userService, times(1)).delete(user)
    }
}
