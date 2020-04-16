Создать список исключений и заполнить его 9 различными runtime исключениями.
Например,

    try {
        int[] arr = new int[5];
        arr[7] = 12;
    } catch (Exception e) {
        exceptionsList.add(e);
    }