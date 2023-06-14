package com.otarbakh.motogp.data.repository.news

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.otarbakh.motogp.common.Constants.API_KEY_NEWS
import com.otarbakh.motogp.common.Constants.STARTING_PAGE_INDEX
import com.otarbakh.motogp.domain.model.ArticleDomain
import com.otarbakh.motogp.data.model.news.toArticleDomain
import com.otarbakh.motogp.data.service.NewsService
import java.io.IOException


class NewsDataSource(private val api: NewsService, private val q:String) : PagingSource<Int, ArticleDomain>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        // ამჟამინდელი გვერდის ნომერი - თუ params.key (ანუ ეხლანდელი გვერდი) ნალია დააბრუნე STARTING_PAGE_INDEX რაც არის 1 (კონსტანტებში)
        val page = params.key ?: STARTING_PAGE_INDEX

        Log.d("gugulisbude","position: $page \n")
        Log.d("gugulisbude","params.key ${params.key} \n")
        Log.d("gugulisbude","params loadsize ${params.loadSize} \n")

        return try {
            //მოგვაქ აპი ქოლი სამი პარამეტრით - 1) დასასერჩი სიტყვა, 2) ერთ გვერდზე ჩასატვირთი ნიუსების რაოდენობა 3) აპი ქი
            val response = api.getNews(q,page, params.loadSize, API_KEY_NEWS)
            // ვმაპავთ რესპონსის ბოდის, ანუ უშუალოდ მონაცემებს დომეინ დატაკლასში
            val articles = response.body()!!.articles.map { it.toArticleDomain() }

            //ვსაზღვრავთ შემდეგი გვერდის გამოთვლის ფორმულას - თუ სია ცარიელია ვაბრუნებთ ნალს.
            // ანუ თუ მივაღწიეთ სიის ბოლოს, ანუ თუ მეტი ნიუსი აღარ გვაქვს.
            // თუ ჯერ კიდე გვაქვს სია, ანუ ნუისები ისევ არის, მაშინ არსებულ გვერდს, რომელზეც იუზერი დგას დავუმატოთ 1
            val nextKey =
                if (articles.isEmpty()) {
                    null
                } else {
                    page + 1
                }
            // ვსაზღვრავთ წინა გვერდს. აქ მარტივია - თუ ამჟამად რაზეც ვართ ეგ გვერდი არის სტარტ ინდექსი ანუ 1, ანუ პირველ გვერდზე ვდგავართ,
            // მაშინ წინა გვერდი უნდა გახდეს ნალი, იმიტორო ლოგიკურად არუნდა იარსებოს წინა გვერდმა.
            //და თუ ახლანდელი გვერდი არის 1 ისგან განსხვავებული ანუ ნებსიმიერ სხვა გვერდზე ვართ გარდა პირველისა, წინა გვერდი გავხადოთ ამ გვერდს მინუს ერთი.

            val prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1

            Log.d("gugulisbude","nextKey: $nextKey \n")
            Log.d("gugulisbude","prevKey: $prevKey \n \n")


            // ამით ვალოადებთ მონაცემებს პეიჯინგში.
            // 1) ანუ ჩვენ სიას (articles) ვატანთ დატა პარამეტრს.
            // 2) შემდეგი გვერდის ინფოს
            // 3) წინა გვერდის ინფოს

            LoadResult.Page(
                data = articles,
                prevKey = prevKey,
                nextKey = nextKey
            )



        } catch (exception: IOException) {
            val error = IOException("Please Check Internet Connection")
            LoadResult.Error(error)
        } catch (exception: retrofit2.HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}


// როგორც კი სია რაზეცაა იმ ფრაგმენტს გავხსნით ეგრევე ლოგქათში მოდის ეს მონაცემები:

// params.key null - თავიდან ეს ნალია სულ
// position: 1 - რომელი გვერდია ახლა
// params loadsize 9 - რამდენი ნიუსი ჩაიტვირთა ერთ გვერდზე (ეს პირველად არის 3 * NETWORK_PAGE_SIZE ანუ ჩვენთან 9 და მერე ხდება NETWORK_PAGE_SIZE ანუ 3)
// nextKey: 2 - შემდეგი გვერდის მანიშნებელი
// prevKey: null - წინა გვერდის მანიშნებელი (პირველ გვერდზე ეს არის სულ ნალი)

// როგორც კი ოდნავ ჩასქროლავ ლოგქათში ეს ინფო  მოდის:

// params.key 2 - გაუტოლდა იმ გვერდს რომელი გვერდიცაა ეხლა. (აღარაა ნალი იმიტორო დაიწყო ათვლა)
// position: 2 - რომელი გვერდია ახლა
// params loadsize 3 - აღარაა 9 და არის 3 ანუ NETWORK_PAGE_SIZE.
// nextKey: 3 - ეს უკვე 3 გახდა იმიტორო ახლა რაზეც ვდგავართ მეორე გვერდია და შესაბამისად 2+1
// prevKey: 1 - წინა გვერდი გახდა 1 იმიტორო ეხლა ვართ გვერდ 2-ზე შესაბამისად 2-1