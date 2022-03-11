package org.example.service.impl;

import org.example.service.OrderService;
import org.springframework.stereotype.Service;

@Service("CustomServiceImpl")
public class CustomServiceImpl extends AbstractCustomService implements OrderService {
}
