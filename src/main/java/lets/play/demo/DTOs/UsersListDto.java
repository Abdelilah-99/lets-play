package lets.play.demo.DTOs;

import java.util.List;

import lets.play.demo.Entity.User;

public record UsersListDto(List<User> users) {
}