package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 기준으로 UserApiResponse 생성 후 return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // 1. request data
        UserApiRequest userApiRequest = request.getData();

        // 2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 기준으로 UserApiResponse 생성 후 return
        // 해당 부분은 read나 update 부분에서도 사용할 수 있기 때문에 바깥에 작성
        return Header.OK((response(newUser)));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id)
                .map(user -> response(user))
                // .map(userApiResponse -> Header.OK(userApiResponse))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음'"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // 1. data 가져오기
        UserApiRequest userApiRequest = request.getData();

        // 2. id를 가지고 user 데이터 찾기
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        // 3. update
        // 4. userApiResponse
        return optional.map(user -> {
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPassword(userApiRequest.getPassword())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        })
                .map(user -> userRepository.save(user))     // update -> updateUser
                .map(updateUser -> response(updateUser))    // userApiResponse
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        // 1. id를 가지고 repository를 통해서 user 찾기
        Optional<User> optional = userRepository.findById(id);

        // 2. repository를 통해서 delete
        // 3. responser return
        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private UserApiResponse response(User user) {
        // user -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        // List<UserApiResponse>
        // Header<List<UserApiResponse>>

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList, pagination);

    }
}
