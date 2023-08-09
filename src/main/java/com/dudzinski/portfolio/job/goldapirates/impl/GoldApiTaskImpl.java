package com.dudzinski.portfolio.job.goldapirates.impl;

//
//@Service
//GoldApiTaskImpl implements GoldApiTask {
//
//    private final GoldApi goldApi;
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//    @Autowired
//    public GoldApiTaskImpl(GoldApi goldApi) {
//        this.goldApi = goldApi;
//    }
//
//    @Scheduled(fixedDelay = 120000)
//    @Override
//    public void updateGoldApiRates() {
//        System.out.println("Pobieranie z Api MetalApi rozpoczęło się: " + LocalDateTime.now().format(formatter));
//        goldApi.getNewestRates();
//    }
//}
//