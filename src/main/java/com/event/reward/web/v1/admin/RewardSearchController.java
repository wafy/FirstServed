package com.event.reward.web.v1.admin;

import com.event.reward.axiom.target.ForAdmin;
import com.event.reward.core.Reward;
import com.event.reward.core.query.RewardPageSearcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("adminRewardSearchController")
@RequestMapping("/v1/admin/reward")
@RequiredArgsConstructor
public class RewardSearchController implements ForAdmin {
    private final RewardPageSearcher rewardPageSearcher;

    /**
     * 리워드 상세 조회해 리턴합니다.
     * @param rewardId 조회할 리워드 아이디
     * @return 조회 결과 리워드
     */
    @GetMapping("/{rewardId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> find(@PathVariable Long rewardId) {

        final Optional<Reward> reward = rewardPageSearcher.find(rewardId);
        if (reward.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new RewardListResponse(reward.get()));
    }

    /**
     * 여러개의 리워드를 조회해 리턴합니다.
     * @param request 조회 요청
     * @return 조회 결과 리워드 목록
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<RewardListResponse> find(@ModelAttribute RewardSearchRequest request) {
        Sort sort;
        if (request.getSort() != null && request.getSort().equals("desc")) {
            sort = Sort.by("created").descending();
        } else {
            sort = Sort.by("created").ascending();
        }

        Pageable pageable = PageRequest.of(request.getPage(),
                request.getSize(),
                sort);

        Page<Reward> rewards = rewardPageSearcher.find(
                request.getStartAt(),
                request.getEndAt(),
                pageable);

        return rewards.map(RewardListResponse::new);
    }
}
