package com.yuri.gongbu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import com.yuri.gongbu.web.dto.WordsListResponseDto;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.domain.words.Words;

@RequiredArgsConstructor
@Service
public class WordsService {

    private final WordsRepository wordsRepository;

    public Map<String, Object> findByDeleteFlgZero(Pageable pageable) {
        Page<Words> result = wordsRepository.findByDeleteFlg(GlobalVariable.FALSE, pageable);
        Map<String, Object> resultMap = new HashMap<>();
        List<WordsListResponseDto> words = result.stream().map(WordsListResponseDto::new).collect(Collectors.toList());

        resultMap.put("words", words);
        resultMap.put("isFirst", result.isFirst());
        resultMap.put("isLast", result.isLast());
        resultMap.put("hasNext", result.hasNext());
        resultMap.put("hasPrevious", result.hasPrevious());
        resultMap.put("getNumber", result.getNumber());
        resultMap.put("totalPages", result.getTotalPages());

        return resultMap;
    }

    public Map<String, Object> findByDeleteFlgAndWordNameLike(String keyWord, Pageable pageable) {
        Page<Words> result = wordsRepository.findByDeleteFlgAndWordNameContaining(GlobalVariable.FALSE, keyWord, pageable);
        Map<String, Object> resultMap = new HashMap<>();
        List<WordsListResponseDto> words = result.stream().map(WordsListResponseDto::new).collect(Collectors.toList());

        resultMap.put("words", words);
        resultMap.put("isFirst", result.isFirst());
        resultMap.put("isLast", result.isLast());
        resultMap.put("hasNext", result.hasNext());
        resultMap.put("hasPrevious", result.hasPrevious());
        resultMap.put("getNumber", result.getNumber());
        resultMap.put("totalPages", result.getTotalPages());

        return resultMap;
    }
}