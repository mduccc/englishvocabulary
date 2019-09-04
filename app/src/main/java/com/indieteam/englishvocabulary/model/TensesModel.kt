package com.indieteam.englishvocabulary.model

class TensesModel {
    companion object {
        class SimplePresent {
            companion object {
                const val title = "THÌ HIỆN TẠI ĐƠN"
                const val description = ""
                const val look = "Trong câu có xuất hiện từ sau: every (every day, every week, every month, ...)\n" +
                        "\n" +
                        "Các trạng từ tần suất xuất hiện trong thì hiện tại đơn:  Always , usually, often, sometimes, seldom, rarely, hardly, never."
                const val manual = "- Diễn tả một sự thật hiển nhiên, một chân lý. st" +
                        "\n" +
                        "- Diễn tả 1 thói quen, một hành động thường xảy ra ở hiện tại" +
                        "\n" +
                        "- Nói lên khả năng của một người"
                const val affirmation = "S + am/are/is + V_S/ES + O \nS + do/does + V_S/ES + O"
                const val example1 = "I am a student."
                const val negative = "S+ am/are/is + not + V(Infinitive) +O\n S+ do/does + not + V(Infinitive) +O"
                const val example2 = "She is not beautiful."
                const val question = "Am/is/are + S + (an/a/the) N (s)/ Adj? \nDo/does + S + (an/a/the) N (s)/ Adj?"
                const val example3 = "Are you a student?"
            }
        }

        class PresentContinuous {
            companion object {
                const val title = "THÌ HIỆN TẠI TIẾP DIỄN"
                const val description = ""
                const val look = "Trong câu có những từ: Now, right now, at present, at the moment,\n" +
                        "\n" +
                        "Trong các câu trước đó là một câu chỉ mệnh lệnh: Look!, Watch! Be quite!, ...\n"
                const val manual =
                    "- Diễn tả hành động đang diễn ra tại thời điểm nói (E.g: The children are playing football now.)\n" +
                            "- Diễn tả hành động đang diễn ra nhưng không nhất thiết xảy ra tại thời điểm nói. (E.g: I am looking for a job)\n" +
                            "- Diễn tả 1 sự than phiền với hành động lặp đi lặp lại nhiều gây khó chịu, bực mình. Trong trường hợp này, câu thường có trạng từ tần suất \"always\". (E.g : He is always borrowing our books and then he doesn’t remember.)\n" +
                            "- Dùng để diễn tả một hành động sắp xảy ra trong tương lai theo kế hoạch đã định trước (E.g: I am flying to Thailand tomorrow.)"
                const val affirmation = "S + am/are/is + V_ing + O"
                const val example1 = "They are studying English."
                const val negative = "S+ am/are/is + not + V_ing + O"
                const val example2 = "I’m not cooking now."
                const val question = "Am/are/is + S+ V_ing + O?"
                const val example3 = "Is she watching T.V at the moment?"
            }
        }

        class PresentPerfect {
            companion object {
                const val title = "THÌ HIỆN TẠI HOÀN THÀNH"
                const val description = ""
                const val look =
                    "Trong thì hiện tại hoàn thành thường có những từ sau: Already, yet, just, ever, never, since, for, recently, ...\n" +
                            "\n" +
                            "- just, recently, lately: gần đây, vừa mới\n" +
                            "- ever: đã từng\n" +
                            "- already: rồi\n" +
                            "- for + khoảng thời gian (for a year, for a long time, …)\n" +
                            "- since + mốc/điểm thời gian(since 1992, since June, …)\n" +
                            "- yet: chưa (dùng trong câu phủ định và câu hỏi)\n" +
                            "- so far = until now = up to now = up to the present: cho đến bây giờ"
                const val manual =
                    "- Diễn tả hành động xảy ra trong quá khứ nhưng không nói rõ thời điểm, có kết quả liên quan đến hiện tại:\n" +
                            "Ví dụ: : My car has been stolen./ Chiếc xe của tôi đã bị lấy cắp.\n" +
                            "\n" +
                            "- Diễn tả hành động bắt đầu ở quá khứ và đang tiếp tục ở hiện tại :\n" +
                            "Ví dụ: They’ve been married for nearly fifty years / Họ đã kết hôn được 50 năm rồi."
                const val affirmation = "S + have/has + V3 + O"
                const val example1 = "She has studied English for 2 years."
                const val negative = "S + have/has + not + V3 + O"
                const val example2 = "I haven’t seen my close friend for a month."
                const val question = "Have/has + S + V3 + O? \n(What, where, who, why,...) + Have/has + S + V3 + O?"
                const val example3 = "Have you eaten dinner yet? \nHow long has she been here?"
            }
        }

        class PastSimple {
            companion object {
                const val title = "QUÁ KHỨ ĐƠN"
                const val description = ""
                const val look =
                    "Các từ thường xuất hiện trong thì quá khứ đơn: Yesterday ( hôm qua), last (night/ week/ month/ year), ago (cách đây), ..."
                const val manual = "Diễn tả hành động sự vật xác định trong quá khứ hoặc vừa mới kết thúc. \n" +
                        "\n" +
                        "Ví dụ:\n" +
                        "\n" +
                        "I went to the concert last week.\n" +
                        "A few weeks ago, a woman called to report a robbery."
                const val affirmation = "S + was/were + O \nS + V(past)+ O"
                const val example1 = "They were in Paris on their summer holiday last year. \nHe worked as a policeman."
                const val negative = "S + was/were + not + O \nS + did + not + V (infinitive)"
                const val example2 = "We weren’t at home yesterday. \nShe didn’t eat bread for the breakfast."
                const val question = "Was/were + S + O ? \nDid + S + V (infinitive)+ O ?"
                const val example3 = "Were they at work yesterday? \nDid you call Zoey yesterday?"

                const val affirmation2 = "S + WAS/WERE + (an/a/the) + N(s)/ Adj"
                const val example11 = ""
                const val negative2 = " S+ WAS/ WERE + NOT + (an/a/the) + N(s)/ Adj"
                const val example22 = ""
                const val question2 = "WAS/WERE + S+ (an/a/the) + N(s)/ Adj?"
                const val example33 = ""
            }
        }

        class PastContinuous {
            companion object {
                const val title = "THÌ QUÁ  KHỨ TIẾP DIỄN"
                const val description = ""
                const val look = "Trong câu có những từ: While, when, as, at 10:00 (giờ) last night, ...\n" +
                        "Ví dụ:\n" +
                        "\n" +
                        "+ It happened at five the afternoon while she was watching the news on TV.\n" +
                        "+ He was doing his homework in his bedroom when the burglar came into the house."
                const val manual =
                    "- Dùng để diễn tả một hành động đang xảy ra tại một thời điểm xác định trong quá khứ.\n" +
                            "\n" +
                            "Ví dụ:  At 12 o’clock yesterday, we were having lunch. (Vào lúc 12h ngày hôm qua, chúng tôi đang ăn trưa.)\n" +
                            "\n" +
                            "- Dùng để diễn tả một hành động đang xảy ra thì một hành động khác xen vào. Hành động đang xảy ra chia thì quá khứ tiếp diễn, hành động xen vào chia thì quá khứ đơn.\n" +
                            "Ex:  He was chatting with his friend when his mother came into the room. (Cậu ta đang tán gẫu với bạn khi mẹ cậu ta vào phòng.)"
                const val affirmation = "S + was/were + V_ing + O"
                const val example1 = "She was watching the news at 7 o'clock yesterday."
                const val negative = "S + wasn’t/weren’t+ V-ing + O"
                const val example2 = "he weren’t watching the news at 7 o'clock yesterday."
                const val question = "Was/Were + S+ V-ing + O?"
                const val example3 = "Were you watching the news at 7 o'clock yesterday?"
            }
        }

        class PastPerfect {
            companion object {
                const val title = "QUÁ KHỨ HOÀN THÀNH"
                const val description = ""
                const val look = "Dấu hiện nhận biết thì quá khứ hoàn thành:\n" +
                        "\n" +
                        "- Từ nhận biết: until then, by the time, prior to that time, before, after, for, as soon as, by, ...\n" +
                        "- Trong câu thường có các từ: before, after, when by, by the time, by the end of + time in the past\n" +
                        "Ví dụ:\n" +
                        "\n" +
                        "+ The old tenant had vacated the property by the time we lookd at it. There was no furniture left inside\n" +
                        "\n" +
                        "+ When I got up this morning, my father had already left."
                const val manual =
                    "Diễn tả một hành động đã xảy ra, hoàn thành trước một hành động khác trong quá khứ.\n" +
                            "\n" +
                            "- Khi hai hành động cùng xảy ra trong quá khứ, ta dùng thì quá khứ hoàn thành cho hành động xảy ra trước và quá khứ đơn cho hành động xảy ra sau.\n" +
                            "\n" +
                            "- Khi thì quá khứ hoàn thành thường được dùng kết hợp với thì quá khứ đơn, ta thường dùng kèm với các giới từ và liên từ như: by (có nghĩa như before), before, after, when, till, untill, as soon as, no sooner…than\n" +
                            "\n" +
                            "Ex: Yesterday, I went out after I had finished my homework. (Hôm qua, tôi đi chơi sau khi tôi đã làm xong bài tập.)"
                const val affirmation = "S + had + V2/ED + O"
                const val example1 = "They had gone to school before they went home."
                const val negative = "S + had+ not + V2/ED + O"
                const val example2 = "They hadn’t eaten breakfast before they went to school."
                const val question = " Had +S + V2/ED + O?"
                const val example3 = "Had they eaten breakfast before they went to school?"
            }
        }

        class SimpleFuture {
            companion object {
                const val title = "TƯƠNG LAI ĐƠN"
                const val description = ""
                const val look =
                    "Trong câu thường có: tomorrow, Next day/ Next week/ next month/ next year,  in + thời gian, probably, perhaps, ..."
                const val manual =
                    "- Diễn tả dự định nhất thời xảy ra ngay tại lúc nói. (EX: Are you going to the beach? I will go with you – Bạn muốn đi biển không? Mình sẽ đi cùng bạn.)\n" +
                            "- Nói về một dự đoán dựa trên kinh nghiệm có được trong quá khứ. (EX: I think it’ll be extremely hot there – Tôi nghĩ rằng nó sẽ rất nóng đó)\n" +
                            "- Khi đưa ra ý kiến, đề nghị. (EX: Don’t worry, I’ll let everyone know - Đừng lo lắng, tôi sẽ cho tất cả mọi người biết\n" +
                            "Lưu ý:\n" +
                            "\n" +
                            "Thì tương lai đơn có thể sử dụng để diễn tả cả hành động có dự định và không có dự định từ trước. Tuy nhiên để phân biệt với thì tương lai gần có dự định, thì tương lai đơn thường được dùng cho các hành động mang tính bộc phát ngay tại thời điểm nói chứ không có dự định như thì tương lai gần. Ví dụ:\n" +
                            "\n" +
                            "+ Mother: There is no sugar left. (Hết đường mất rồi.)\n" +
                            "\n" +
                            "+ Son: Ok, I will go to market and buy it for you. (Con sẽ ra chợ mua cho mẹ.)"
                const val affirmation = "S + shall/will + V(infinitive) + O"
                const val example1 = "We’ ll enjoy it."
                const val negative = "S + shall/will + not + V(infinitive) + O"
                const val example2 = "He won’t go to school."
                const val question = "Shall/will+S + V(infinitive) + O?"
                const val example3 = "Will they have dinner together?"
            }
        }

        class FutureContinuous {
            companion object {
                const val title = "THÌ TƯƠNG LAI TIẾP DIỄN"
                const val description = ""
                const val look =
                    "Trong câu có các trạng từ chỉ thời gian trong tương lai kèm theo thời điểm xác định:\n" +
                            "\n" +
                            "- at this time/ at this moment + thời gian trong tương lai: Vào thời điểm này ….\n" +
                            "\n" +
                            "- At + giờ cụ thể + thời gian trong tương lai: vào lúc …..\n" +
                            "\n" +
                            "Ví dụ:\n" +
                            "\n" +
                            "- At this time tomorrow I will be watching my favorite TV show. (Vào thời điểm này ngày mai, tôi đang xem chương trình TV yếu thích của mình.)\n" +
                            "\n" +
                            "- At 6 a.m tomorrow, I will be running in the park. (Vào 6h sáng mai, tôi đang chạy bộ trong công viên)"
                const val manual = "" +
                        "- Dùng để nói về một hành động xảy ra trong tương lai tại thời điểm xác định. (EX: At 10 o’clock tomorrow, my friends and I will be going to the museum.)\n" +
                        "- Dùng nói về một hành động đang xảy ra trong tương lai thì có hành động khác xen vào. (EX:When you come tomorrow, I will be watching my favorite TV show.)"
                const val affirmation = "S + shall/will + be + V-ing+ O "
                const val example1 = " I will be staying at the hotel in Nha Trang at 1 p.m tomorrow."
                const val negative = "S + shall/will + not + be + V-ing + O"
                const val example2 = "We won’t be studying at 8 a.m tomorrow."
                const val question = "Shall/Will+S + be + V-ing + O?"
                const val example3 = "Will you be waiting for the train at 9 a.m next Monday?"
            }
        }

        class FuturePerfect {
            companion object {
                const val title = "THÌ TƯƠNG LAI HOÀN THÀNH"
                const val description = ""
                const val look = "- By + thời gian tương lai,\n" +
                        "- By the end of + thời gian trong tương lai,\n" +
                        "- Before + thời gian tương lai\n" +
                        "- By the time …"
                const val manual =
                    "- Dùng để diễn tả một hành động hay sự việc hoàn thành trước một thời điểm trong tương lai.\n" +
                            "Ví dụ: I will have finished my homework before 10 o’clock this evening. (Cho đến trước 10h tối nay tôi sẽ hoàn thành xong bài tập về nhà rồi.)\n" +
                            "\n" +
                            "- Dùng để diễn tả một hành động hay sự việc hoàn thành trước một hành động hay sự việc khác trong tương lai.\n" +
                            "Ví dụ: I will have made the meal ready before the time you come tomorrow. (Ngày mai tôi đã chuẩn bị bữa ăn sẵn sàng trước khi bạn đến ngày mai.)"
                const val affirmation = "S + shall/will + have + V3/ED"
                const val example1 = "I will have finished my report by the end of this month."
                const val negative = "S + shall/will + not + have + V3/ED"
                const val example2 = "I will not have stopped my work before you come tomorrow."
                const val question = "Shall/Will+ S + have + V3/ED ?"
                const val example3 = "Will you have gone out by 7 pm tomorrow?"
            }
        }

    }

}