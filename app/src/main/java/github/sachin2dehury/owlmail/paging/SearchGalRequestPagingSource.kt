package github.sachin2dehury.owlmail.paging

import github.sachin2dehury.owlmail.api.ZimbraApi
import github.sachin2dehury.owlmail.data.remote.searchgal.Contact
import github.sachin2dehury.owlmail.database.ContactDao
import github.sachin2dehury.owlmail.utils.NetworkState
import github.sachin2dehury.owlmail.utils.getZimbraSearchGalRequest
import kotlinx.coroutines.flow.Flow

class SearchGalRequestPagingSource(
    private val contactDao: ContactDao,
    networkStateFlow: Flow<NetworkState>,
    private val query: String?,
    private val zimbraApi: ZimbraApi,
) : ZimbraPagingSource<Contact>(networkStateFlow) {

    override suspend fun loadPageFromRemote(offset: Int, limit: Int): LoadResult<Int, Contact> {
        val request = getZimbraSearchGalRequest(query, offset, limit)
        val response = zimbraApi.makeSearchRequest(request).body()?.body?.searchGalResponse
//        coroutineScope { launch { contactDao.insertContact(response?.contact) } }
        return LoadResult.Page(
            data = response?.contact ?: emptyList(),
            prevKey = if (offset == 0) null else offset - 1,
            nextKey = if (response?.hasMore == true) offset + 1 else null,
        )
    }

    override suspend fun loadPageFromLocal(params: LoadParams<Int>) = when (query.isNullOrEmpty()) {
        true -> contactDao.getContact().load(params)
        else -> contactDao.searchContact(query).load(params)
    }
}
