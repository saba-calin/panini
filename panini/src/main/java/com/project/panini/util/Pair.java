package com.project.panini.util;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pair<K, V> {

    private K first;

    private V second;
}
