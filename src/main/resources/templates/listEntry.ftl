<#-- @ftlvariable name="username" type="String" -->
<#-- @ftlvariable name="listEntry" type="uk.jamesgarden.randomanimepicker.listentry.ListEntry" -->
<#include 'layout/base.ftl'>

<#if listEntry.animeTitleEng?has_content>
  <#assign pageTitle=listEntry.animeTitleEng>
<#else>
  <#assign pageTitle=listEntry.animeTitle!"Unknown Anime">
</#if>

<@baseTemplate
  pageTitle=pageTitle
>
  <div class="list-entry">
    <div class="anime-image">
      <@image.image
        imageUrl=listEntry.animeImagePath
        altText=listEntry.animeTitle!"Unknown"
        class="anime-image"
      />
    </div>
    <div class="anime-info">
      <#if listEntry.animeTitleEng?has_content>
        <div class="anime-title">
          <h1>${listEntry.animeTitleEng}</h1>
        </div>
        <div class="anime-title-jp">
          <h2>${listEntry.animeTitle}</h2>
        </div>
      <#else>
        <div class="anime-title-jp">
          <h1>${listEntry.animeTitle}</h1>
        </div>
      </#if>
      <@table.table class="anime-details">
        <@table.simpleRow keyText="Status" valueText=listEntry.status.displayName />
        <@table.simpleRow keyText="Score" valueText=listEntry.animeScoreVal />
        <@table.simpleRow keyText="Number of Episodes" valueText=listEntry.animeNumEpisodes />
        <@table.simpleRow keyText="Date Added to List" valueText=listEntry.createdAt />
        <@table.simpleRow keyText="Airing Status" valueText=listEntry.animeAiringStatus.displayName />
        <@table.simpleRow keyText="Age Rating" valueText=listEntry.animeMpaaRatingString!"" />
      </@table.table>
    </div>
  </div>
</@baseTemplate>
