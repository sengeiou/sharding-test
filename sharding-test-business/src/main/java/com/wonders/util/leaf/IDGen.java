package com.wonders.util.leaf;

import com.wonders.util.leaf.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
